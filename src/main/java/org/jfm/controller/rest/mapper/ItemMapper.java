package org.jfm.controller.rest.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.controller.rest.dto.ItemCreateUpdateDto;
import org.jfm.domain.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = QuarkusMappingConfig.class)
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    Item toDomain(ItemCreateUpdateDto item);

}
