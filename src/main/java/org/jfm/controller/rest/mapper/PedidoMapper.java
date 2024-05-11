package org.jfm.controller.rest.mapper;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.controller.rest.dto.PedidoDTO;
import org.jfm.domain.entities.Pedido;
import org.mapstruct.Mapper;

@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoMapper {
    
    Pedido toDomain(PedidoDTO pedido);

}
