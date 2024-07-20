package org.jfm.infra.repository.adaptersql.mapper;

import java.util.UUID;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.jfm.infra.repository.adaptersql.entities.PedidoStatusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoStatusMapper {

    @Mapping(target =  "idPedido", source = "pedidoStatus.pedido.id")
    PedidoStatus toDomain(PedidoStatusEntity pedidoStatus);

    @Mapping(target = "pedido", source = "idPedido", qualifiedByName = "pedidoUuidToEntity")
    PedidoStatusEntity toEntity(PedidoStatus pedidoStatus);

    @Named("pedidoUuidToEntity")
    public static PedidoEntity pedidoUuidToEntity(UUID idPedido) {
        if (idPedido == null) {
            return null;
        }

        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(idPedido);
        return pedidoEntity;
    }

}
