package com.fillipe.pagmodulo.domain.valueobject;

public record Link(
        String rel,
        String href,
        String method
) {
}
