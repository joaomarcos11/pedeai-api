package org.jfm.controller.rest.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.controller.rest.dto.PedidoCreateDto;
import org.jfm.controller.rest.dto.PedidoDto;
import org.jfm.controller.rest.dto.PedidoUpdateDto;
import org.jfm.domain.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoMapper {

    Pedido toDomain(PedidoDto pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    Pedido toDomain(PedidoCreateDto pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idCliente", ignore = true)
    Pedido toDomain(PedidoUpdateDto pedido);

    @Mapping(target = "itens", ignore = true)
    PedidoDto toDto(Pedido pedido);

}
