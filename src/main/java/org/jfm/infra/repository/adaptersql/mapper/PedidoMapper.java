package org.jfm.infra.repository.adaptersql.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.bootloader.QuarkusMappingConfig;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.infra.repository.adaptersql.entities.ClienteEntity;
import org.jfm.infra.repository.adaptersql.entities.ItemEntity;
import org.jfm.infra.repository.adaptersql.entities.ItemPedidoEntity;
import org.jfm.infra.repository.adaptersql.entities.ItemPedidoKey;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = QuarkusMappingConfig.class)
public interface PedidoMapper {
    
    @Mapping(target = "idCliente", source = "pedido.cliente.id")
    @Mapping(target = "itens", source = "itensPedidos", qualifiedByName = "itensEntityToUuid")
    Pedido toDomain(PedidoEntity pedido, @Context ItemMapper itemMapper);

    @Named("itensEntityToUuid")
    public static Map<Item, Integer> itensEntityToUuid(Set<ItemPedidoEntity> itemPedido, @Context ItemMapper itemMapper) {
        return itemPedido.stream().collect(Collectors.toMap(e -> itemMapper.toDomain(e.getItem()), ItemPedidoEntity::getQuantidade));
    }

    @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "clienteUuidToEntity")
    @Mapping(target = "itensPedidos", source = "itens", qualifiedByName = "itensToItemPedidoEntity")
    PedidoEntity toEntity(Pedido pedido, @Context UUID pedidoUuid, @Context ItemMapper itemMapper);

    @Named("clienteUuidToEntity")
    public static ClienteEntity clienteUuidToEntity(UUID idCliente) {
        if (idCliente == null) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(idCliente);
        return clienteEntity;
    }

    @Named("itensToItemPedidoEntity")
    public static Set<ItemPedidoEntity> itensToItemPedidoEntity(Map<Item, Integer> itens, @Context UUID pedidoUuid, @Context ItemMapper itemMapper) {
        if (itens == null) {
            return new HashSet<ItemPedidoEntity>();
        }

        List<ItemPedidoEntity> itensPedidos = new ArrayList<>();
        for (Item item : itens.keySet()) {
            ItemPedidoEntity itemPedido = new ItemPedidoEntity();
            itemPedido.setId(new ItemPedidoKey());
            
            ItemEntity ie = new ItemEntity();
            ie.setId(item.getId());
            itemPedido.setItem(ie);
            
            PedidoEntity pe = new PedidoEntity();
            pe.setId(pedidoUuid);
            itemPedido.setPedido(pe);

            itemPedido.setQuantidade(itens.get(item));

            itensPedidos.add(itemPedido);
        }

        return new HashSet<ItemPedidoEntity>(itensPedidos);
    }

}