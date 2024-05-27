package org.jfm.controller.rest.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.controller.rest.dto.ItemPedidoCreateDto;
import org.jfm.controller.rest.dto.ItemPedidoDto;
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
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "itens", source = "itensPedidos", qualifiedByName = "itensPedidosListToMap")
    Pedido toDomain(PedidoCreateDto pedido);

    @Named("itensPedidosListToMap")
    public static Map<Item, Integer> itensPedidosListToMap(List<ItemPedidoCreateDto> itensPedidosDto) {
        if (itensPedidosDto == null) {
            return null;
        }

        Map<Item, Integer> itensPedidos = new HashMap<>();
        
        for (ItemPedidoCreateDto itemPedidoCreateDto : itensPedidosDto) {
            Item item = new Item();
            item.setId(itemPedidoCreateDto.idItem());
            itensPedidos.put(item, itemPedidoCreateDto.quantidade());
        }

        return itensPedidos;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idCliente", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "itens", ignore = true)
    Pedido toDomain(PedidoUpdateDto pedido);

    @Mapping(target = "itens", qualifiedByName = "itensMapping")
    PedidoDto toDto(Pedido pedido);

    @Named("itensMapping")
    public static List<ItemPedidoDto> itensMapping(Map<Item, Integer> itens) {
        List<ItemPedidoDto> itensPedidos = new ArrayList<>();

        for (Item item : itens.keySet()) {
            ItemPedidoDto itemPedidoDto = new ItemPedidoDto(item.getNome(), item.getCategoria(), item.getPreco(), itens.get(item));
            itensPedidos.add(itemPedidoDto);
        }

        return itensPedidos;
    }

}
