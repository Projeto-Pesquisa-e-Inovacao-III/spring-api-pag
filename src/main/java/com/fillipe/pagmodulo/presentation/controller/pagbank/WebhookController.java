package com.fillipe.pagmodulo.presentation.controller.pagbank;

import com.fillipe.pagmodulo.application.dto.order.request.ReqOrderDto;
import com.fillipe.pagmodulo.application.dto.checkout.request.ReqUpdateCheckoutDto;
import com.fillipe.pagmodulo.application.usecase.UpdateCheckoutStatus.UpdateCheckoutStatusUseCase;
import com.fillipe.pagmodulo.presentation.mapper.order.OrderMapper;
import com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderCommand;
import com.fillipe.pagmodulo.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webhook/pagbank")
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final PaymentOrderWebhookUseCase paymentOrderWebhookUseCase;
    private final UpdateCheckoutStatusUseCase updateCheckoutStatusUseCase;

    public WebhookController(PaymentOrderWebhookUseCase paymentOrderWebhookUseCase,
                             UpdateCheckoutStatusUseCase updateCheckoutStatusUseCase) {
        this.paymentOrderWebhookUseCase = paymentOrderWebhookUseCase;
        this.updateCheckoutStatusUseCase = updateCheckoutStatusUseCase;
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCheckout(@RequestBody ReqUpdateCheckoutDto dto) {
        log.info("Update webhook recebido | dto: {}", dto);
        UpdateCheckoutStatusUseCase.UpdateCheckoutStatusCommand cmd = new UpdateCheckoutStatusUseCase.UpdateCheckoutStatusCommand(
                dto.id(),
                dto.referenceId(),
                dto.status()
        );

        updateCheckoutStatusUseCase.execute(cmd);
    }

    @PostMapping(value = "/payment-update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void paymentUpdateCheckout(@RequestBody ReqOrderDto dto) {
        log.info("Pagamento webhook recebido | dto: {}", dto);

            PaymentOrderCommand cmd = OrderMapper.toCommand(dto);
            paymentOrderWebhookUseCase.execute(cmd);
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
