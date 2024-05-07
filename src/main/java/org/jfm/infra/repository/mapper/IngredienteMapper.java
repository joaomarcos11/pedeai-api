package org.jfm.infra.repository.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Ingrediente;
import org.jfm.infra.repository.entities.IngredienteEntity;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface IngredienteMapper {

    Ingrediente toDomain(IngredienteEntity ingrediente);

    IngredienteEntity toEntity(Ingrediente ingrediente);

}
