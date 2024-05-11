package org.jfm.infra.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.ports.ItemPedidoRepository;
import org.jfm.domain.valueObjects.ItemPedido;
import org.jfm.infra.repository.entities.ItemPedidoEntity;
import org.jfm.infra.repository.mapper.ItemPedidoMapper;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class ItemPedidoRepositoryImpl implements ItemPedidoRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    ItemPedidoMapper itemPedidoMapper;

    @Override
    @Transactional
    public void criar(ItemPedido itemPedido) {
        entityManager.persist(itemPedidoMapper.toEntity(itemPedido));
    }

    @Override
    @Transactional
    public List<Item> listarPorPedidoId(UUID idPedido) {
        // TODO: implementar isso aqui vai ser tricky
        return entityManager.createNamedQuery("ItemPedido.findAll", ItemPedidoEntity.class)
        .getResultStream().map(i -> itemPedidoMapper.toDomain(i)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remover(ItemPedido itemPedido) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
    
}
