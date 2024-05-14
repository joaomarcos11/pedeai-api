package org.jfm.infra.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.infra.repository.entities.ItemEntity;
import org.jfm.infra.repository.mapper.ItemMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ItemRepositoryImpl implements ItemRepository {
    @Inject
    EntityManager entityManager;

    @Inject
    ItemMapper itemMapper;

    @Override
    @Transactional
    public void criar(Item item) {
        entityManager.persist(itemMapper.toEntity(item));
    }

    @Override
    @Transactional
    public List<Item> listar() {
        return entityManager.createNamedQuery("Item.findAll", ItemEntity.class)
                .getResultStream().map(i -> itemMapper.toDomain(i)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Item buscarPorId(UUID id) {
        TypedQuery<ItemEntity> query = entityManager.createNamedQuery("Item.findById", ItemEntity.class);
        query.setParameter("id", id);

        return itemMapper.toDomain(query.getSingleResult());
    }

    @Override
    @Transactional
    public void editar(Item item) {
        Query query = entityManager.createNamedQuery("Item.update");
        query.setParameter("id", item.getId());
        query.setParameter("nome", item.getNome());
        query.setParameter("preco", item.getPreco());
        query.setParameter("categoria", item.getCategoria());

        query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
    }

    @Override
    @Transactional
    public void remover(Item item) {
        Query query = entityManager.createNamedQuery("Item.delete");
        query.setParameter("id", item.getId());
        query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
    }

    @Override
    @Transactional
    public List<Item> listarPorCategoria(Categoria categoria) {
        TypedQuery<ItemEntity> query = entityManager.createNamedQuery("Item.findByCategoria", ItemEntity.class);
        query.setParameter("categoria", categoria);

        return query.getResultStream()
                .map(i -> itemMapper.toDomain(i)).collect(Collectors.toList());
    }

}
