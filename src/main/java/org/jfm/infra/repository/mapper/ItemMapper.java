package org.jfm.infra.repository.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Item;
import org.jfm.infra.repository.entities.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface ItemMapper {
    Item toDomain(ItemEntity item);

    ItemEntity toEntity(Item item);
}
