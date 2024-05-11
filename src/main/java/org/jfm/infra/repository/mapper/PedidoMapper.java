package org.jfm.infra.repository.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Pedido;
import org.jfm.infra.repository.entities.PedidoEntity;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoMapper {
    Pedido toDomain(PedidoEntity pedido);

    PedidoEntity toEntity(Pedido pedido);
}
