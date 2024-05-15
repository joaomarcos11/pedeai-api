package org.jfm.infra.repository.adaptersql.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Pedido;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoMapper {
    @Mapping(target = "idCliente", expression = "java(pedido.getCliente().getId())")
    Pedido toDomain(PedidoEntity pedido);

    @Mapping(target = "cliente", ignore = true)
    // @Mapping(target = "itens", ignore = true)
    PedidoEntity toEntity(Pedido pedido);
}
