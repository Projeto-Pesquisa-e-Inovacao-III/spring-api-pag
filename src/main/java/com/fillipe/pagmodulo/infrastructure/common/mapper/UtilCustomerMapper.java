package com.fillipe.pagmodulo.infrastructure.common.mapper;

import com.fillipe.pagmodulo.domain.shared.valueobjects.TaxDocument;
import com.fillipe.pagmodulo.domain.shared.valueobjects.TaxDocumentType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UtilCustomerMapper {

    @Named("extractTaxDocumentValue")
    default String extractTaxDocumentValue(TaxDocument taxDocument) {
        if (taxDocument == null) return null;
        return taxDocument.getValue();
    }

    @Named("toTaxDocument")
    default TaxDocument toTaxDocument(String taxId) {
        if (taxId == null) return null;
        return new TaxDocument(taxId, TaxDocumentType.CPF);
    }
}
