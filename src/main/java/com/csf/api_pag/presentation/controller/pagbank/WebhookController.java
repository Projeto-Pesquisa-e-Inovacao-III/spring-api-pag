package com.csf.api_pag.presentation.controller.pagbank;

import com.csf.api_pag.presentation.dto.order.request.ReqOrderDto;
import com.csf.api_pag.presentation.dto.checkout.request.ReqUpdateCheckoutDto;
import com.csf.api_pag.application.usecase.UpdateCheckoutStatus.UpdateCheckoutStatusUseCase;
import com.csf.api_pag.presentation.controller.pagbank.api.WebhookApi;
import com.csf.api_pag.presentation.mapper.order.OrderMapper;
import com.csf.api_pag.application.usecase.PaymentOrderWebhook.PaymentOrderCommand;
import com.csf.api_pag.application.usecase.PaymentOrderWebhook.PaymentOrderWebhookUseCase;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/webhook/pagbank")
public class WebhookController implements WebhookApi {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);

    private final PaymentOrderWebhookUseCase paymentOrderWebhookUseCase;
    private final UpdateCheckoutStatusUseCase updateCheckoutStatusUseCase;

    public WebhookController(PaymentOrderWebhookUseCase paymentOrderWebhookUseCase,
                             UpdateCheckoutStatusUseCase updateCheckoutStatusUseCase) {
        this.paymentOrderWebhookUseCase = paymentOrderWebhookUseCase;
        this.updateCheckoutStatusUseCase = updateCheckoutStatusUseCase;
    }

    @Override
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateCheckout(@Valid @RequestBody ReqUpdateCheckoutDto dto) {
        log.info("Update webhook recebido | dto: {}", dto);
        UpdateCheckoutStatusUseCase.UpdateCheckoutStatusCommand cmd = new UpdateCheckoutStatusUseCase.UpdateCheckoutStatusCommand(
                dto.id(),
                dto.referenceId(),
                dto.status()
        );

        updateCheckoutStatusUseCase.execute(cmd);
    }

    @Override
    @PostMapping(value = "/payment-update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void paymentUpdateCheckout(@Valid @RequestBody ReqOrderDto dto) {
        log.info("Pagamento webhook recebido | dto: {}", dto);

            PaymentOrderCommand cmd = OrderMapper.toCommand(dto);
            paymentOrderWebhookUseCase.execute(cmd);
    }

    @Override
    @PostMapping(value = "/payment-update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void paymentUpdateCheckoutNotification(
            @RequestParam String notificationCode,
            @RequestParam String notificationType) {

        log.info("Notificação legada recebida | notificationCode: {} | notificationType: {}",
                notificationCode, notificationType);
    }
}
