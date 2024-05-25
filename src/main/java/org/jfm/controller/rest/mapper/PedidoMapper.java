package org.jfm.controller.rest.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.controller.rest.dto.PedidoCreateDto;
import org.jfm.controller.rest.dto.PedidoDto;
import org.jfm.controller.rest.dto.PedidoUpdateDto;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Pedido toDomain(PedidoCreateDto pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "itens", ignore = true)
    Pedido toDomain(PedidoUpdateDto pedido);

    @Mapping(target = "itens", qualifiedByName = "itensMapping")
    PedidoDto toDto(Pedido pedido);

    @Named("itensMapping")
    public static Map<Item, Integer> itensMapping(Map<UUID, Integer> itens) {
        Map<Item, Integer> itemQuantidade = new HashMap<>();
        
        for (UUID idItem : itens.keySet()) {
            Item item = new Item();
            item.setId(idItem);
            itemQuantidade.put(item, itens.get(idItem));
        }

        return itemQuantidade;
    }

}
