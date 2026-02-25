package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.CustomerDto;
import com.fillipe.pagmodulo.domain.valueobject.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerDto toCustomerDTO(Customer customer);

    Customer toCustomer(CustomerDto customerDTO);
}

