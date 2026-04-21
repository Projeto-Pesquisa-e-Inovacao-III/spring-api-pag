package com.csf.api_pag.presentation.mapper.shared;

import com.csf.api_pag.presentation.dto.shared.CustomerDto;
import com.csf.api_pag.domain.shared.valueobjects.Customer;

public class CustomerMapper {

    private CustomerMapper() {}

    public static CustomerDto toCustomerDTO(Customer customer) {
        if (customer == null) return null;
        return new CustomerDto(
                customer.externalCustomerId(),
                customer.name(),
                customer.email(),
                TaxDocumentMapper.toTaxDocumentDTO(customer.taxDocument()),
                PhoneMapper.toPhoneDTO(customer.phone())
        );
    }

    public static Customer toCustomer(CustomerDto customerDTO) {
        if (customerDTO == null) return null;
        return new Customer(
                customerDTO.externalCustomerId(),
                customerDTO.name(),
                customerDTO.email(),
                TaxDocumentMapper.toTaxDocument(customerDTO.taxDocument()),
                PhoneMapper.toPhone(customerDTO.phone())
        );
    }
}
