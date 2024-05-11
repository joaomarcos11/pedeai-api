package org.jfm.controller.rest.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.controller.rest.dto.ItemDTO;
import org.jfm.domain.entities.Item;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface ItemMapper {

    Item toDomain(ItemDTO item);

}
