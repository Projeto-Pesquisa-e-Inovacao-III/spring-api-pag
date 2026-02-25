package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.ItemDto;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toItemDTO(Item item);

    Item toItem(ItemDto itemDTO);

    List<ItemDto> toItemDTOList(List<Item> items);

    List<Item> toItemList(List<ItemDto> itemDTOs);
}

