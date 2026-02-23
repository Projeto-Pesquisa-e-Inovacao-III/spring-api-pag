package com.fillipe.pagmodulo.application.usecase.CreateCheckout;

import com.fillipe.pagmodulo.domain.entity.Checkout;
import com.fillipe.pagmodulo.domain.port.CheckoutGateway;
import com.fillipe.pagmodulo.domain.port.CheckoutRepository;

public class CreateCheckoutUseCase {

    private final CheckoutRepository checkoutRepository;
    private final CheckoutGateway checkoutGateway; // abstração!

    public CreateCheckoutUseCase(CheckoutRepository checkoutRepository,
                                 CheckoutGateway checkoutGateway) {
        this.checkoutRepository = checkoutRepository;
        this.checkoutGateway = checkoutGateway;
    }

    public Checkout execute(CreateCheckoutCommand command) {
        Checkout checkout = new Checkout(
                command.customer(),
                command.items(),
                command.additionalAmount(),
                command.discountAmount(),
                command.paymentMethods(),
                command.softDescriptor(),
                command.redirectUrl(),
                command.returnUrl(),
                command.notificationUrls(),
                command.paymentNotificationUrls()
        );

        Checkout activatedCheckout = checkoutGateway.register(checkout);
        return checkoutRepository.save(activatedCheckout);
    }
}

