package com.fillipe.pagmodulo.infrastructure.pagbank;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.domain.port.out.CheckoutGateway;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.request.ReqPagBankCheckoutDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankCheckoutDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankErrorDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.exeception.*;
import com.fillipe.pagmodulo.infrastructure.pagbank.mapper.PagBankCheckoutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class PagBankCheckoutGateway implements CheckoutGateway {

    private static final Logger log = LoggerFactory.getLogger(PagBankCheckoutGateway.class);

    private final RestClient restClient;
    private final String apiToken;
    private final PagBankCheckoutMapper mapper;
    private final ObjectMapper objectMapper;

    public PagBankCheckoutGateway(
            @Value("${pagbank.api.url}") String apiUrl,
            @Value("${pagbank.api.token}") String apiToken,
            PagBankCheckoutMapper mapper,
            ObjectMapper objectMapper
    ) {
        this.mapper = mapper;
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder().baseUrl(apiUrl).build();
        this.apiToken = apiToken;
    }

    @Override
    public Checkout register(Checkout checkout) {
        ReqPagBankCheckoutDto request = mapper.toReqPagBankCheckoutRegisterDto(checkout);

        log.info("Checkout dados: {}", checkout.toString());

        RestClient.RequestHeadersSpec<?> spec = restClient.post()
                .uri("/checkouts")
                .header("Authorization", "Bearer " + apiToken)
                .body(request);

        ResPagBankCheckoutDto response = executeRequest(
                spec,
                ResPagBankCheckoutDto.class,
                "registro de checkout"
        );

        log.info("Pagbank: {}", response);

        return mapper.toDomain(response);
    }

    @Override
    public Checkout sync(String gatewayId) {
        RestClient.RequestHeadersSpec<?> spec = restClient.get()
                .uri("/checkouts/" + gatewayId)
                .header("Authorization", "Bearer " + apiToken);

        ResPagBankCheckoutDto response = executeRequest(
                spec,
                ResPagBankCheckoutDto.class,
                "sincronização de checkout com ID: " + gatewayId
        );

        return mapper.toDomain(response);
    }

    private <T> T executeRequest(
            RestClient.RequestHeadersSpec<?> spec,
            Class<T> responseType,
            String operation) {

        try {
            T response = spec
                    .retrieve()
                    .onStatus(status -> status.value() == 401, (req, res) -> {
                        throw new PagBankAuthenticationException("Falha na autenticação para: " + operation);
                    })
                    .onStatus(status -> status.value() == 400, (req, res) -> {
                        throw parseValidationError(res);
                    })
                    .onStatus(status -> status.value() == 404, (req, res) -> {
                        throw new PagBankNotFoundException(operation);
                    })
                    .onStatus(status -> status.value() == 429, (req, res) -> {
                        throw new PagBankRateLimitException(
                                "Limite de requisições excedido para: " + operation,
                                parseRetryAfter(res)
                        );
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                        throw new PagBankServerException(
                                "Erro no servidor para: " + operation,
                                res.getStatusCode().value()
                        );
                    })
                    .body(responseType);

            if (response == null) {
                log.error("PagBank retornou resposta nula para: {}", operation);
                throw new PagBankUnknownException("Resposta nula do PagBank para: " + operation);
            }

            return response;

        } catch (PagBankException e) {
            throw e;
        } catch (ResourceAccessException e) {
            throw new PagBankTimeoutException("Tempo esgotado para: " + operation, e);
        } catch (RestClientException e) {
            throw new PagBankUnknownException("Erro inesperado para: " + operation, e);
        }
    }


    private PagBankValidationException parseValidationError(ClientHttpResponse response) {
        try {
            ResPagBankErrorDto errorResponse = objectMapper.readValue(
                    response.getBody(),
                    ResPagBankErrorDto.class
            );

            if (errorResponse.errorMessages() != null && !errorResponse.errorMessages().isEmpty()) {
                ResPagBankErrorDto.ErrorDetail firstError = errorResponse.errorMessages().getFirst();
                return new PagBankValidationException(
                        firstError.parameterName(),
                        firstError.error(),
                        firstError.description()
                );
            }

            return new PagBankValidationException(
                    "desconhecido",
                    "erro_validacao",
                    "Falha na validação mas os detalhes do erro não puderam ser analisados"
            );

        } catch (IOException e) {
            log.warn("Failed to parse PagBank validation error response", e);
            return new PagBankValidationException(
                    "desconhecido",
                    "erro_analise",
                    "Não foi possível analisar a resposta de erro do PagBank"
            );
        }
    }

    private Integer parseRetryAfter(ClientHttpResponse response) {
        String retryAfter = response.getHeaders().getFirst("Retry-After");
        if (retryAfter != null) {
            try {
                return Integer.parseInt(retryAfter);
            } catch (NumberFormatException e) {
                log.warn("Invalid Retry-After header: {}", retryAfter);
            }
        }
        return null;
    }
}
