package com.csf.pagmodulo.infrastructure.pagbank.mapper;

import com.csf.pagmodulo.domain.shared.valueobjects.Customer;
import com.csf.pagmodulo.domain.shared.valueobjects.Phone;
import com.csf.pagmodulo.infrastructure.pagbank.dto.common.PagBankCustomerDto;
import com.csf.pagmodulo.infrastructure.pagbank.dto.common.PagBankPhoneDto;
import com.csf.pagmodulo.infrastructure.common.mapper.UtilCustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UtilCustomerMapper.class})
public interface PagBankCustomerMapper {

    @Mapping(source = "taxDocument", target = "taxId", qualifiedByName = "extractTaxDocumentValue")
    PagBankCustomerDto toReqPagBankCustomerDto(Customer customer);

    PagBankPhoneDto toReqPagBankPhoneDto(Phone phone);

    @Mapping(source = "dto.taxId", target = "taxDocument", qualifiedByName = "toTaxDocument")
    @Mapping(source = "externalCustomerId", target = "externalCustomerId")
    Customer toCustomerDomain(PagBankCustomerDto dto, String externalCustomerId);

    Phone toPhoneDomain(PagBankPhoneDto dto);
}
