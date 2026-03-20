package com.csf.pagmodulo.domain.model.checkout;

import com.csf.pagmodulo.domain.checkout.entity.Checkout;
import com.csf.pagmodulo.domain.checkout.enums.CheckoutStatus;
import com.csf.pagmodulo.domain.shared.valueobjects.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class CheckoutTestBuilder {

    private final Checkout.Builder builder;

    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.ofHours(-3);

    private CheckoutTestBuilder() {
        // Start with valid defaults
        this.builder = Checkout.newCheckout()
                .customer(validCustomer())
                .items(List.of(validItem()));

    }

    public static CheckoutTestBuilder aCheckout() {
        return new CheckoutTestBuilder();
    }

    public static CheckoutTestBuilder anExpiredCheckout() {
        return new CheckoutTestBuilder()
                .withExpirationDate(OffsetDateTime.now(ZONE_OFFSET).minusHours(1));
    }

    public CheckoutTestBuilder withCustomer(Customer customer) {
        this.builder.customer(customer);
        return this;
    }

    public CheckoutTestBuilder withItems(Item... items) {
        this.builder.items(List.of(items));
        return this;
    }

    public CheckoutTestBuilder withNoItems() {
        this.builder.items(List.of());
        return this;
    }

//    public CheckoutTestBuilder withPaymentMethods(PaymentMethod... methods) {
//        this.builder.paymentMethods(List.of(methods));
//        return this;
//    }
//
//    public CheckoutTestBuilder withNoPaymentMethods() {
//        this.builder.paymentMethods(List.of());
//        return this;
//    }

    public CheckoutTestBuilder withExpirationDate(OffsetDateTime expirationDate) {
        this.builder.expirationDate(expirationDate);
        return this;
    }

    public CheckoutTestBuilder withStatus(CheckoutStatus status) {
        this.builder.status(status);
        return this;
    }

    public CheckoutTestBuilder withGatewayId(String gatewayId) {
        this.builder.gatewayId(gatewayId);
        return this;
    }

    public Checkout build() {
        return this.builder.build();
    }

    private static Customer validCustomer() {
        return new Customer(
                "1",
                "João",
                "João@example.com",
                new TaxDocument("024.216.140-58", TaxDocumentType.CPF),
                new Phone("+55","11", "912769028")
        );
    }

    private static Item validItem() {
        return new Item("1", "Produto", "Test produto", 1, 10000);
    }
}
