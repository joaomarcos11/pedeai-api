package org.jfm.infra.repository.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Item;
import org.jfm.infra.repository.entities.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = QuarkusMappingConfig.class)
public interface ItemMapper {
    Item toDomain(ItemEntity item);

    @Mapping(target = "pedidos", ignore = true)
    ItemEntity toEntity(Item item);
}
