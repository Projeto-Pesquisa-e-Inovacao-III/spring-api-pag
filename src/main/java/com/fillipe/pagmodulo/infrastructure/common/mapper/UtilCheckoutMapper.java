package com.fillipe.pagmodulo.infrastructure.common.mapper;

import com.fillipe.pagmodulo.domain.checkout.entity.CheckoutStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UtilCheckoutMapper {

    @Named("toStatus")
    default CheckoutStatus toStatus(String status) {
        if (status == null) {
            return null;
        }
        return switch (status.toUpperCase()) {
            case "ACTIVE" -> CheckoutStatus.ACTIVE;
            case "INACTIVE" -> CheckoutStatus.INACTIVE;
            case "EXPIRED" -> CheckoutStatus.EXPIRED;
            default -> CheckoutStatus.CREATING;
        };
    }
}
