package com.fillipe.pagmodulo.infrastructure.common.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Customer;
import com.fillipe.pagmodulo.domain.valueobject.TaxDocument;
import com.fillipe.pagmodulo.domain.valueobject.TaxDocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Named("extractTaxDocumentValue")
    default String extractTaxDocumentValue(TaxDocument taxDocument) {
        if (taxDocument == null) return null;
        return taxDocument.value();
    }

    @Named("toTaxDocument")
    default TaxDocument toTaxDocument(String taxId) {
        if (taxId == null) return null;
        return new TaxDocument(taxId, TaxDocumentType.CPF);
    }
}
