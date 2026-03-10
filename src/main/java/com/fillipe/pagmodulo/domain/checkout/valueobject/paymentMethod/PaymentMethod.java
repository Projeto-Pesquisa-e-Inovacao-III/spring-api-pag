package com.fillipe.pagmodulo.domain.checkout.valueobject.paymentMethod;


public sealed interface PaymentMethod permits PixPaymentMethod, ConfigurableCardPaymentMethod, BoletoPaymentMethod {}
