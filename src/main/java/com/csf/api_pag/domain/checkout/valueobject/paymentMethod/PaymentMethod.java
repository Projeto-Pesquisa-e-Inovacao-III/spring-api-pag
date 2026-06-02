package com.csf.api_pag.domain.checkout.valueobject.paymentMethod;


public sealed interface PaymentMethod permits PixPaymentMethod, ConfigurableCardPaymentMethod, BoletoPaymentMethod {}
