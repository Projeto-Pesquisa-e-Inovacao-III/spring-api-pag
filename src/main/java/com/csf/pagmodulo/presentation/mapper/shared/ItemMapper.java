package com.csf.pagmodulo.presentation.mapper.shared;

import com.csf.pagmodulo.application.dto.shared.ItemDto;
import com.csf.pagmodulo.domain.shared.valueobjects.Item;

import java.util.List;
import java.util.stream.Collectors;

public class ItemMapper {

    private ItemMapper() {}

    public static ItemDto toItemDTO(Item item) {
        if (item == null) return null;
        return new ItemDto(
                item.externalItemId(),
                item.name(),
                item.description(),
                item.quantity(),
                item.unitAmount(),
                item.imageUrl()
        );
    }

    public static Item toItem(ItemDto itemDTO) {
        if (itemDTO == null) return null;
        return new Item(
                itemDTO.externalItemId(),
                itemDTO.name(),
                itemDTO.description(),
                itemDTO.quantity(),
                itemDTO.unitAmount(),
                itemDTO.imageUrl()
        );
    }

    public static List<ItemDto> toItemDTOList(List<Item> items) {
        if (items == null) return List.of();
        return items.stream().map(ItemMapper::toItemDTO).collect(Collectors.toList());
    }

    public static List<Item> toItemList(List<ItemDto> itemDTOs) {
        if (itemDTOs == null) return List.of();
        return itemDTOs.stream().map(ItemMapper::toItem).collect(Collectors.toList());
    }
}
