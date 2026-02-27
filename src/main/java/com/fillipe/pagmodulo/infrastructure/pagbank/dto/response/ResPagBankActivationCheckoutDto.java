package com.fillipe.pagmodulo.infrastructure.pagbank.dto.response;

import java.util.List;

public record ResPagBankActivationCheckoutDto (
        String id,
        String status,
        List<ResPagBankLinkDto> links
)
{ }
