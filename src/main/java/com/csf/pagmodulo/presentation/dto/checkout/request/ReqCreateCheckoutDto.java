package com.csf.pagmodulo.presentation.dto.checkout.request;

import com.csf.pagmodulo.presentation.dto.shared.CustomerDto;
import com.csf.pagmodulo.presentation.dto.shared.ItemDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ReqCreateCheckoutDto(
        @NotNull(message = "O cliente é obrigatório")
        @Valid
        CustomerDto customer,

        @NotEmpty(message = "A lista de itens é obrigatória e não pode estar vazia")
        @Valid
        List<ItemDto> items,
        Integer additionalAmount,
        Integer discountAmount
) {}

