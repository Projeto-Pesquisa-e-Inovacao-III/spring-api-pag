package com.fillipe.pagmodulo.application.mapper.Checkout;

import com.fillipe.pagmodulo.application.dto.checkout.PhoneDto;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Phone;

public class PhoneMapper {

    private PhoneMapper() {}

    public static PhoneDto toPhoneDTO(Phone phone) {
        if (phone == null) return null;
        return new PhoneDto(phone.country(), phone.area(), phone.number());
    }

    public static Phone toPhone(PhoneDto phoneDTO) {
        if (phoneDTO == null) return null;
        return new Phone(phoneDTO.country(), phoneDTO.area(), phoneDTO.number());
    }
}
