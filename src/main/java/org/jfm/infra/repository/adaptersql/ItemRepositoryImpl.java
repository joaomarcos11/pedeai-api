package org.jfm.infra.repository.adaptersql;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.infra.repository.adaptersql.entities.ItemEntity;
import org.jfm.infra.repository.adaptersql.mapper.ItemMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
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
        try {
            entityManager.persist(itemMapper.toEntity(item));
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Item> listar() {
        try {
            return entityManager.createNamedQuery("Item.findAll", ItemEntity.class)
               .getResultStream().map(i -> itemMapper.toDomain(i)).collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public Item buscarPorId(UUID id) {
        try {

            TypedQuery<ItemEntity> query = entityManager.createNamedQuery("Item.findById", ItemEntity.class);
            query.setParameter("id", id);
            
            return itemMapper.toDomain(query.getSingleResult());
        } catch(NoResultException e) {
            throw new EntityNotFoundException(ErrosSistemaEnum.ITEM_NOT_FOUND.getMessage());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void editar(Item item) {
        try {

            Query query = entityManager.createNamedQuery("Item.update");
            query.setParameter("id", item.getId());
            query.setParameter("nome", item.getNome());
            query.setParameter("preco", item.getPreco());
            query.setParameter("categoria", item.getCategoria());
            query.setParameter("descricao", item.getDescricao());
            query.setParameter("imagem", item.getImagem());
            
            query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void remover(Item item) {
        try {
            Query query = entityManager.createNamedQuery("Item.delete");
            query.setParameter("id", item.getId());
            query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Item> listarPorCategoria(Categoria categoria) {
        try {
            TypedQuery<ItemEntity> query = entityManager.createNamedQuery("Item.findByCategoria", ItemEntity.class);
            query.setParameter("categoria", categoria);
            
            return query.getResultStream()
               .map(i -> itemMapper.toDomain(i)).collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

}
