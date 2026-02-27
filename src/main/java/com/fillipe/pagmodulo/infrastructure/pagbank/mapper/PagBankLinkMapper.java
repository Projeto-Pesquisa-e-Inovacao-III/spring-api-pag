package com.fillipe.pagmodulo.infrastructure.pagbank.mapper;

import com.fillipe.pagmodulo.application.dto.LinkDto;
import com.fillipe.pagmodulo.domain.checkout.valueobject.Link;
import com.fillipe.pagmodulo.infrastructure.pagbank.dto.response.ResPagBankLinkDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagBankLinkMapper {

    Link toLinkDomain(ResPagBankLinkDto dto);
    List<Link> toLinksDomain(List<ResPagBankLinkDto> listDto);

    LinkDto toLinkDto(ResPagBankLinkDto dto);
    List<LinkDto> toLinksDto(List<ResPagBankLinkDto> dtos);
}
