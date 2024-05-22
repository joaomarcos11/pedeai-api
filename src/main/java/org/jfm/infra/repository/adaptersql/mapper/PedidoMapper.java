package org.jfm.infra.repository.adaptersql.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.bootloader.QuarkusMappingConfig;
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

    // @Mapping(target = "idCliente", expression = "java(pedido.getCliente().getId())")
    @Mapping(target = "idCliente", source = "pedido.cliente.id")
    @Mapping(target = "itens", qualifiedByName = "itensEntityToUuid")
    Pedido toDomain(PedidoEntity pedido);

    @Named("itensEntityToUuid")
    public static Map<UUID, Integer> itensEntityToUuid(Set<ItemPedidoEntity> itemPedido) {
           return itemPedido.stream().collect(Collectors.toMap(e -> e.getId().getItem().getId(), ItemPedidoEntity::getQuantidade));
    }

    // ---

    @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "clienteUuidToEntity")
    @Mapping(target = "itens", qualifiedByName = "itensUuidToEntity")
    PedidoEntity toEntity(Pedido pedido, @Context UUID idPedido);

    @Named("clienteUuidToEntity")
    public static ClienteEntity clienteUuidToEntity(UUID idCliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(idCliente);
        return clienteEntity;
    }

    @Named("itensUuidToEntity")
    public static Set<ItemPedidoEntity> itensUuidToEntity(Map<UUID, Integer> itens, @Context UUID idPedido) {
        if (itens == null) {
            return new HashSet<ItemPedidoEntity>();
        }

        System.out.println("entrando no Mapper");
        
        List<ItemPedidoEntity> itensPedidos = new ArrayList<>();
        for (UUID idItem : itens.keySet()) {

            ItemEntity item = new ItemEntity();
            item.setId(idItem);

            PedidoEntity pedido = new PedidoEntity();
            pedido.setId(idPedido);

            ItemPedidoEntity itemPedido = new ItemPedidoEntity(item, pedido, itens.get(idItem));
            itensPedidos.add(itemPedido);
        }

        System.out.println("saindo do mapper");
        return new HashSet<ItemPedidoEntity>(itensPedidos);
    }
    
    // @Mapping(target = "idCliente", expression = "java(pedido.getCliente().getId())")
    // @Mapping(target = "itens", ignore = true)
    // Pedido toDomainIgnoreItens(PedidoEntity pedido);

    // @Mapping(target = "itens", ignore = true)
    // @Mapping(target = "cliente", ignore = true)
    // PedidoEntity toEntityIgnoreClienteAndItens(Pedido pedido);

}