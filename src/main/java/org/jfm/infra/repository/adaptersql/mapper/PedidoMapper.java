package org.jfm.infra.repository.adaptersql.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Pedido;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoMapper {
    
    @Mapping(target = "idCliente", expression = "java(pedido.getCliente().getId())")
    @Mapping(target = "itens", ignore = true)
    Pedido toDomainIgnoreItens(PedidoEntity pedido);

    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    PedidoEntity toEntityIgnoreClienteAndItens(Pedido pedido);
}
