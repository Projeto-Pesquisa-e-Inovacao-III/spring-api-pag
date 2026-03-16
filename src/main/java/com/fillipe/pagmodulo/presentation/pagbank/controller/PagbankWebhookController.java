package com.fillipe.pagmodulo.presentation.pagbank.controller;

import com.fillipe.pagmodulo.application.dto.order.request.ReqOrderDto;
import com.fillipe.pagmodulo.presentation.mapper.order.OrderMapper;
import com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderCommand;
import com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import com.fillipe.pagmodulo.domain.shared.exceptions.InvalidFieldException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/webhook/pagbank")
public class PagbankWebhookController {

    private static final Logger log = LoggerFactory.getLogger(PagbankWebhookController.class);

    private final PaymentOrderWebhookUseCase paymentOrderWebhookUseCase;
    private final ObjectMapper objectMapper;

    public PagbankWebhookController(PaymentOrderWebhookUseCase paymentOrderWebhookUseCase, ObjectMapper objectMapper) {
        this.paymentOrderWebhookUseCase = paymentOrderWebhookUseCase;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCheckout() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/payment-update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void paymentUpdateCheckout(@RequestBody String rawBody) {
        log.info("Pagamento webhook recebido | rawBody: {}", rawBody);

        try {
            ReqOrderDto dto = objectMapper.readValue(rawBody, ReqOrderDto.class);
            PaymentOrderCommand cmd = OrderMapper.toCommand(dto);
            paymentOrderWebhookUseCase.execute(cmd);
        } catch (RuntimeException e) {
            throw new InvalidFieldException("body", "JSON inválido no webhook de pagamento");
        }
    }

    @PostMapping(value = "/payment-update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void paymentUpdateCheckoutNotification(
            @RequestParam String notificationCode,
            @RequestParam String notificationType) {
        log.info("Notificação legada recebida | notificationCode: {} | notificationType: {}",
                notificationCode, notificationType);
    }
}
