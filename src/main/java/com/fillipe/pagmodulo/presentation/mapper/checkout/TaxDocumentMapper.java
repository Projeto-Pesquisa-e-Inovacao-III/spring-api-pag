package com.fillipe.pagmodulo.presentation.mapper.checkout;

import com.fillipe.pagmodulo.application.dto.shared.TaxDocumentDto;
import com.fillipe.pagmodulo.domain.shared.valueobjects.TaxDocument;

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
