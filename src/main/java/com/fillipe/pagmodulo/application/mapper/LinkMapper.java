package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.LinkDto;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Link;

import java.util.List;
import java.util.stream.Collectors;

public class LinkMapper {

    private LinkMapper() {}

    public static LinkDto toLinkDTO(Link link) {
        if (link == null) return null;
        return new LinkDto(link.rel(), link.href(), link.method());
    }

    public static List<LinkDto> toLinkDTOList(List<Link> links) {
        if (links == null) return null;
        return links.stream().map(LinkMapper::toLinkDTO).collect(Collectors.toList());
    }

    public static Link toDomain(LinkDto dto) {
        if (dto == null) return null;
        return new Link(dto.rel(), dto.href(), dto.method());
    }

    public static List<Link> toListDomain(List<LinkDto> dtos) {
        if (dtos == null) return null;
        return dtos.stream().map(LinkMapper::toDomain).collect(Collectors.toList());
    }
}
