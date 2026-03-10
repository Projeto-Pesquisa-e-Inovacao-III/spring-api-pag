package com.fillipe.pagmodulo.domain.checkout.valueobject.paymentMethod;

import java.util.List;

public sealed interface ConfigurableCardPaymentMethod extends PaymentMethod
permits CreditCardPaymentMethod, DebitCardPaymentMethod {
    List<PaymentConfigOption> configOptions();
}
