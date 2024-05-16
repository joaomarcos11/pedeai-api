package org.jfm.infra.repository.adaptersql;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.jfm.infra.repository.adaptersql.mapper.PedidoMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoRepositoryImpl implements PedidoRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public void criar(Pedido pedido) {
        try {
            entityManager.persist(pedidoMapper.toEntity(pedido));
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Pedido> listar() {
        try {
            return entityManager.createNamedQuery("Pedido.findAll", PedidoEntity.class).getResultStream()
                    .map(p -> pedidoMapper.toDomain(p)).collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public Pedido buscarPorId(UUID id) {
        try {
            TypedQuery<PedidoEntity> query = entityManager.createNamedQuery("Pedido.findById", PedidoEntity.class);
            query.setParameter("id", id);
    
            return pedidoMapper.toDomain(query.getSingleResult());
        } catch (NoResultException e) {
            throw new EntityNotFoundException(ErrosSistemaEnum.PEDIDO_NOT_FOUND.getMessage());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Pedido> listarPorStatus(Status status) {
        try {
            TypedQuery<PedidoEntity> query = entityManager.createNamedQuery("Pedido.findByStatus", PedidoEntity.class);
            query.setParameter("status", status);
            
            return query.getResultStream().map(p -> pedidoMapper.toDomain(p)).collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void editar(Pedido pedido) {
        try {

            Query query = entityManager.createNamedQuery("Pedido.update");
            query.setParameter("id", pedido.getId());
            // query.setParameter("idCliente", pedido.getIdCliente());
            query.setParameter("status", pedido.getStatus());
            
            query.executeUpdate();
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

}
