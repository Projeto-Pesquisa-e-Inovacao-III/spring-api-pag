package com.fillipe.pagmodulo.application.usecase.CreateCheckout;

import com.fillipe.pagmodulo.domain.checkout.entity.Checkout;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutGateway;
import com.fillipe.pagmodulo.domain.checkout.port.out.CheckoutRepository;

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
                .additionalAmount(command.additionalAmount())
                .discountAmount(command.discountAmount())
                .build();

        Checkout registered = checkoutGateway.register(createdCheckout);
        return checkoutRepository.save(registered);
    }
}

