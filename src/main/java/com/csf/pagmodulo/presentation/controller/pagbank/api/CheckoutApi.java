package com.csf.pagmodulo.presentation.controller.pagbank.api;

import com.csf.pagmodulo.application.dto.checkout.request.ReqCreateCheckoutDto;
import com.csf.pagmodulo.application.dto.checkout.response.ResActivateCheckoutDto;
import com.csf.pagmodulo.application.dto.checkout.response.ResCheckoutCreatedDto;
import com.csf.pagmodulo.application.dto.checkout.response.ResCheckoutDto;
import com.csf.pagmodulo.application.dto.checkout.response.ResInactivateCheckoutDto;
import com.csf.pagmodulo.presentation.shared.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "Checkout", description = "Operacoes de criacao e gestao de checkout")
public interface CheckoutApi {

    @Operation(
            summary = "Criar checkout",
            description = "Cria um novo checkout com os dados do pedido e retorna os dados de pagamento."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Checkout criado com sucesso",
                    content = @Content(schema = @Schema(implementation = ResCheckoutCreatedDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados invalidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no processamento",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<ResCheckoutCreatedDto> createCheckout(ReqCreateCheckoutDto request);

    @Operation(
            summary = "Consultar checkout",
            description = "Consulta os detalhes de um checkout pelo UUID (disponível no perfil dev)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302", description = "Checkout encontrado",
                    content = @Content(schema = @Schema(implementation = ResCheckoutDto.class))),
            @ApiResponse(responseCode = "404", description = "Checkout nao encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<ResCheckoutDto> getCheckout(
            @Parameter(description = "UUID do checkout", required = true) UUID uuid
    );

    @Operation(
            summary = "Ativar checkout",
            description = "Ativa um checkout previamente inativo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Checkout ativado",
                    content = @Content(schema = @Schema(implementation = ResActivateCheckoutDto.class))),
            @ApiResponse(responseCode = "404", description = "Checkout nao encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<ResActivateCheckoutDto> activateCheckout(
            @Parameter(description = "UUID do checkout", required = true) UUID uuid
    );

    @Operation(
            summary = "Inativar checkout",
            description = "Inativa um checkout ativo."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Checkout inativado",
                    content = @Content(schema = @Schema(implementation = ResInactivateCheckoutDto.class))),
            @ApiResponse(responseCode = "404", description = "Checkout nao encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<ResInactivateCheckoutDto> inactivateCheckout(
            @Parameter(description = "UUID do checkout", required = true) UUID uuid
    );
}

