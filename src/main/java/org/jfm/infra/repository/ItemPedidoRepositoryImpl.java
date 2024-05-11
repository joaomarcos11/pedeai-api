package org.jfm.infra.repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.ports.ItemPedidoRepository;
import org.jfm.domain.valueObjects.ItemPedido;
import org.jfm.infra.repository.entities.ItemEntity;
import org.jfm.infra.repository.entities.PedidoEntity;
import org.jfm.infra.repository.mapper.ItemMapper;
import org.jfm.infra.repository.mapper.PedidoMapper;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class ItemPedidoRepositoryImpl {

    @Inject
    EntityManager entityManager;

    @Inject
    ItemMapper itemMapper;

    @Inject
    PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public void criar(Item item, Pedido pedido) {
        // TODO: substituir os findById pelo entityManager.find()
        ItemEntity itemEntity = itemMapper.toEntity(item);
        PedidoEntity pedidoEntity = pedidoMapper.toEntity(pedido);
        itemEntity.getPedidos().add(pedidoEntity);
        entityManager.persist(itemEntity); // TODO: precisa adicionar isso aqui?
    }

    @Override
    @Transactional
    public List<Item> listarPorPedidoId(UUID idPedido) { // TODO: ver se passando o Pedido direto fica mais fácil
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setId(idPedido);
        Set<ItemEntity> itens = pedidoEntity.getItems();
        return itens.stream().map(i -> itemMapper.toDomain(i)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remover(UUID idPedido, UUID idItem) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(idItem);

        itemEntity.s
    }
    
}
