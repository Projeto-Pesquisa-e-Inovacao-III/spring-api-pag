package com.fillipe.pagmodulo.application.usecase.CreateCheckout;

import com.fillipe.pagmodulo.application.dto.response.ResCheckoutDto;
import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.domain.port.out.CheckoutGateway;
import com.fillipe.pagmodulo.domain.port.out.CheckoutRepository;

public class CreateCheckoutUseCase {

    private final CheckoutGateway checkoutGateway;
    private final CheckoutRepository checkoutRepository;

    public CreateCheckoutUseCase(CheckoutGateway checkoutGateway, CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
        this.checkoutGateway = checkoutGateway;
    }

    public Checkout execute(CreateCheckoutCommand command) {
        Checkout createdCheckout = Checkout.newCheckout()
                .customer(command.customer())
                .items(command.items())
                .paymentMethods(command.paymentMethods())
                .softDescriptor(command.softDescriptor())
                .redirectUrl(command.redirectUrl())
                .returnUrl(command.returnUrl())
                .notificationUrls(command.notificationUrls())
                .paymentNotificationUrls(command.paymentNotificationUrls())
                .additionalAmount(command.additionalAmount())
                .discountAmount(command.discountAmount())
                .build();

        Checkout activatedCheckout = checkoutGateway.register(createdCheckout);
        return checkoutRepository.save(activatedCheckout);
    }
}

