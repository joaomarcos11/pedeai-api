package org.jfm.infra.repository.adaptersql.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Cliente;
import org.jfm.infra.repository.adaptersql.entities.ClienteEntity;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface ClienteMapper {
    Cliente toDomain(ClienteEntity cliente);

    ClienteEntity toEntity(Cliente cliente);
}
