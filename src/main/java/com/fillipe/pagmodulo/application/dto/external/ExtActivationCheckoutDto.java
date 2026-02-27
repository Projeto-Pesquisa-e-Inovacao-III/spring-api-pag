package com.fillipe.pagmodulo.application.dto.external;

import com.fillipe.pagmodulo.application.dto.LinkDto;
import java.util.List;

public record ExtActivationCheckoutDto(
        String id,
        String status,
        List<LinkDto> links
) { }
