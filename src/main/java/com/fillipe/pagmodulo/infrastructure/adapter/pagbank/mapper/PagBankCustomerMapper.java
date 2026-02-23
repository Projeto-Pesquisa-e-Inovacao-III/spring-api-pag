package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Customer;
import com.fillipe.pagmodulo.domain.valueobject.Phone;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request.ReqPagBankCustomerDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request.ReqPagBankPhoneDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response.ResPagBankCustomerDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response.ResPagBankPhoneDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.CustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class})
public interface PagBankCustomerMapper {

    @Mapping(source = "taxDocument", target = "taxId", qualifiedByName = "extractTaxDocumentValue")
    ReqPagBankCustomerDto toReqPagBankCustomerDto(Customer customer);

    ReqPagBankPhoneDto toReqPagBankPhoneDto(Phone phone);

    @Mapping(source = "taxId", target = "taxDocument", qualifiedByName = "toTaxDocument")
    @Mapping(target = "id", ignore = true)
    Customer toCustomerDomain(ResPagBankCustomerDto dto);

    Phone toPhoneDomain(ResPagBankPhoneDto dto);
}
