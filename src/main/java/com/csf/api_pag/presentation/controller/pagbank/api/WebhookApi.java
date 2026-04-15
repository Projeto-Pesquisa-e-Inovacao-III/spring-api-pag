package com.csf.api_pag.presentation.controller.pagbank.api;

import com.csf.api_pag.presentation.dto.checkout.request.ReqUpdateCheckoutDto;
import com.csf.api_pag.presentation.dto.order.request.ReqOrderDto;
import com.csf.api_pag.presentation.shared.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Webhook PagBank", description = "Endpoints para recebimento de notificacoes do PagBank")
public interface WebhookApi {

    @Operation(
            summary = "Atualizar status do checkout",
            description = "Recebe webhook do PagBank para atualizar o status de um checkout."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Webhook processado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Payload invalido",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Webhook nao autorizado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    void updateCheckout(ReqUpdateCheckoutDto dto);

    @Operation(
            summary = "Atualizacao de pagamento",
            description = "Recebe webhook com os dados completos de pagamento e processa o pedido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento processado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Payload invalido",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Webhook nao autorizado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    void paymentUpdateCheckout(ReqOrderDto dto);

    @Operation(
            summary = "Notificacao legada de pagamento",
            description = "Recebe notificacao legada com codigo e tipo da notificacao de pagamento."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificacao recebida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    void paymentUpdateCheckoutNotification(
            @Parameter(description = "Codigo da notificacao", required = true) String notificationCode,
            @Parameter(description = "Tipo da notificacao", required = true) String notificationType
    );
}

