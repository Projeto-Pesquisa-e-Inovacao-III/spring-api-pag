package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.TaxDocumentDto;
import com.fillipe.pagmodulo.domain.checkout.valueobject.TaxDocument;

public class TaxDocumentMapper {

    private TaxDocumentMapper() {}

    public static TaxDocumentDto toTaxDocumentDTO(TaxDocument taxDocument) {
        if (taxDocument == null) return null;
        return new TaxDocumentDto(taxDocument.getValue(), taxDocument.getType());
    }

    public static TaxDocument toTaxDocument(TaxDocumentDto taxDocumentDTO) {
        if (taxDocumentDTO == null) return null;
        return new TaxDocument(taxDocumentDTO.value(), taxDocumentDTO.type());
    }
}
