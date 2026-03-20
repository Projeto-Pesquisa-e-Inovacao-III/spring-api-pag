package com.csf.pagmodulo.domain.checkout.valueobject.paymentMethod;


public sealed interface PaymentMethod permits PixPaymentMethod, ConfigurableCardPaymentMethod, BoletoPaymentMethod {}
