package com.csf.api_pag.presentation.mapper.shared;

import com.csf.api_pag.presentation.dto.shared.PhoneDto;
import com.csf.api_pag.domain.shared.valueobjects.Phone;

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
