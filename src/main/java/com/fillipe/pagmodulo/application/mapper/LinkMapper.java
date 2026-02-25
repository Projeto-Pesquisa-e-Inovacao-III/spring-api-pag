package com.fillipe.pagmodulo.application.mapper;

import com.fillipe.pagmodulo.application.dto.LinkDto;
import com.fillipe.pagmodulo.domain.valueobject.Link;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    LinkDto toLinkDTO(Link link);

    List<LinkDto> toLinkDTOList(List<Link> links);
}

