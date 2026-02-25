package com.fillipe.pagmodulo.domain.checkout.valueobject;

public record Link(
        String rel,
        String href,
        String method
) {
}
