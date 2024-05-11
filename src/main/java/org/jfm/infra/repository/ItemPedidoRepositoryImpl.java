package org.jfm.infra.repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.ports.ItemPedidoRepository;
import org.jfm.infra.repository.entities.ItemEntity;
import org.jfm.infra.repository.entities.PedidoEntity;
import org.jfm.infra.repository.mapper.ItemMapper;
import org.jfm.infra.repository.mapper.PedidoMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ItemPedidoRepositoryImpl implements ItemPedidoRepository{

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
        ItemEntity itemEntity = entityManager.find(ItemEntity.class, item.getId());
        PedidoEntity pedidoEntity = entityManager.find(PedidoEntity.class, pedido.getId());
        itemEntity.getPedidos().add(pedidoEntity);
        entityManager.persist(itemEntity); // TODO: precisa adicionar isso aqui?
    }

    @Override
    @Transactional
    public List<Item> listarPorPedido(Pedido pedido) { // TODO: ver se passando o Pedido direto fica mais fácil
        PedidoEntity pedidoEntity = entityManager.find(PedidoEntity.class, pedido.getId());
        Set<ItemEntity> itens = pedidoEntity.getItems();
        return itens.stream().map(i -> itemMapper.toDomain(i)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remover(Item item, Pedido pedido) {
        ItemEntity itemEntity = entityManager.find(ItemEntity.class, item.getId());
        PedidoEntity pedidoEntity = entityManager.find(PedidoEntity.class, pedido.getId());
        itemEntity.getPedidos().remove(pedidoEntity);
        entityManager.persist(itemEntity);
    }
    
}
