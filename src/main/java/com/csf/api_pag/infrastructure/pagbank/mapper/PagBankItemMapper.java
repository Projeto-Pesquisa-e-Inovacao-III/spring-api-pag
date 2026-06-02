package com.csf.api_pag.infrastructure.pagbank.mapper;

import com.csf.api_pag.domain.shared.valueobjects.Item;
import com.csf.api_pag.infrastructure.pagbank.dto.common.PagBankItemDto;
import com.csf.api_pag.infrastructure.common.mapper.ConversionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ConversionMapper.class)
public interface PagBankItemMapper {

    @Mapping(source = "externalItemId", target = "referenceId")
    PagBankItemDto toReqPagBankItemDto(Item item);

    List<Item> toItemsDomain(List<PagBankItemDto> items);

    @Mapping(source = "referenceId", target = "externalItemId")
    Item toItemDomain(PagBankItemDto dto);
}
