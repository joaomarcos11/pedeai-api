package org.jfm.controller.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.controller.rest.dto.ClienteDto;
import org.jfm.domain.entities.Cliente;

@Mapper(config = QuarkusMappingConfig.class)
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    Cliente toDomain(ClienteDto cliente);

}
