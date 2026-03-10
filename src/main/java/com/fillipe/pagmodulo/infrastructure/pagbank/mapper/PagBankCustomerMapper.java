package com.fillipe.pagmodulo.infrastructure.pagbank.mapper;

import com.fillipe.pagmodulo.domain.shared.valueobjects.Customer;
import com.fillipe.pagmodulo.domain.shared.valueobjects.Phone;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankCustomerDto;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.common.PagBankPhoneDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.UtilCustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UtilCustomerMapper.class})
public interface PagBankCustomerMapper {

    @Mapping(source = "taxDocument", target = "taxId", qualifiedByName = "extractTaxDocumentValue")
    PagBankCustomerDto toReqPagBankCustomerDto(Customer customer);

    PagBankPhoneDto toReqPagBankPhoneDto(Phone phone);

    @Mapping(source = "taxId", target = "taxDocument", qualifiedByName = "toTaxDocument")
    @Mapping(target = "externalCustomerId", ignore = true)
    Customer toCustomerDomain(PagBankCustomerDto dto);

    Phone toPhoneDomain(PagBankPhoneDto dto);
}
