package com.fillipe.pagmodulo.infrastructure.adapter.pagbank.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Item;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.request.ReqPagBankItemDto;
import com.fillipe.pagmodulo.infrastructure.adapter.pagbank.dto.response.ResPagBankItemDto;
import com.fillipe.pagmodulo.infrastructure.common.mapper.ConversionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ConversionMapper.class)
public interface PagBankItemMapper {

    @Mapping(source = "externalItemId", target = "referenceId")
    ReqPagBankItemDto toReqPagBankItemDto(Item item);

    List<Item> toItemsDomain(List<ResPagBankItemDto> items);

    @Mapping(source = "referenceId", target = "externalItemId", qualifiedByName = "toLong")
    Item toItemDomain(ResPagBankItemDto dto);
}
