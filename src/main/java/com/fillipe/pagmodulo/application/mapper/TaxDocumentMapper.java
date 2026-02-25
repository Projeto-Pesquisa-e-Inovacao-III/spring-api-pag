package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.TaxDocumentDto;
import com.fillipe.pagmodulo.domain.valueobject.TaxDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxDocumentMapper {

    TaxDocumentDto toTaxDocumentDTO(TaxDocument taxDocument);

    TaxDocument toTaxDocument(TaxDocumentDto taxDocumentDTO);
}

