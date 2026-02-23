package com.fillipe.pagmodulo.infrastructure.adapter.pagbank;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.domain.port.CheckoutGateway;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request.ReqPagBankCheckoutRegisterDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response.ResPagBankCheckoutRegisterDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.mapper.PagBankCheckoutMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

@Component
public class PagBankCheckoutGateway implements CheckoutGateway {

    private final RestClient restClient;
    private final String apiToken;
    private final PagBankCheckoutMapper mapper;

    public PagBankCheckoutGateway(
            @Value("${pagbank.api.url}") String apiUrl,
            @Value("${pagbank.api.token}") String apiToken,
            PagBankCheckoutMapper mapper
    ) {
        this.mapper = mapper;
        this.restClient = RestClient.builder().baseUrl(apiUrl).build();
        this.apiToken = apiToken;
    }

    @Override
    public Checkout register(Checkout checkout) {
        ReqPagBankCheckoutRegisterDto request = mapper.toReqPagBankCheckoutRegisterDto(checkout);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
            System.out.println("JSON enviado para o PagBank:\n" + json);
        } catch (Exception e) {
            System.out.println("Erro ao serializar request: " + e.getMessage());
        }

        System.out.println(request.toString());

        ResPagBankCheckoutRegisterDto response = restClient.post()
                .uri("/checkouts")
                .header("Authorization", "Bearer " + apiToken)
                .body(request)
                .retrieve()
                .body(ResPagBankCheckoutRegisterDto.class);


        Checkout out = mapper.toDomain(response);
        System.out.println(out);
        return out;
    }
}
