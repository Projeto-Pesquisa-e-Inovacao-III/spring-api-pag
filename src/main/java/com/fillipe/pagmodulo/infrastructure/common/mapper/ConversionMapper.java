package com.fillipe.pagmodulo.infrastructure.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ConversionMapper {
    @Named("toLong")
    default Long toLong(String longString) {
        if (longString == null) return null;
        return Long.parseLong(longString);
    }

    @Named("toUUID")
    default UUID toUUID(String value) {
        if (value == null) return null;
        return UUID.fromString(value);
    }

    @Named("fromOffsetDateTime")
    default String fromOffsetDateTime(OffsetDateTime value) {
        if (value == null) return null;
        return value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
