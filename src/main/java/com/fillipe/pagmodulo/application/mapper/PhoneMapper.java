package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.PhoneDto;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Phone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneDto toPhoneDTO(Phone phone);

    Phone toPhone(PhoneDto phoneDTO);
}

