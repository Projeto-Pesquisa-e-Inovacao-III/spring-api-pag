package com.csf.api_pag.presentation.dto.checkout;

import com.csf.api_pag.presentation.dto.checkout.enums.PaymentMethodType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PaymentMethodDto(
        @NotNull(message = "O tipo de pagamento é obrigatório")
        PaymentMethodType type,

        @Valid
        List<PaymentConfigOptionDto> configOptions
) {}

