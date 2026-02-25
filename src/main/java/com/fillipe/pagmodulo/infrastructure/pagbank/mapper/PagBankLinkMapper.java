package com.fillipe.pagmodulo.infrastructure.pagbank.mapper;

import com.fillipe.pagmodulo.domain.valueobject.Link;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankLinkDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagBankLinkMapper {

    Link toLinkDomain(ResPagBankLinkDto dto);
    List<Link> toLinksDomain(List<ResPagBankLinkDto> listDto);
}
