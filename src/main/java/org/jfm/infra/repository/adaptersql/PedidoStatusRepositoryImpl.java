package org.jfm.infra.repository.adaptersql;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.PedidoStatusRepository;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.jfm.infra.repository.adaptersql.entities.PedidoStatusEntity;
import org.jfm.infra.repository.adaptersql.mapper.PedidoStatusMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoStatusRepositoryImpl implements PedidoStatusRepository {
    
    @Inject
    EntityManager entityManager;

    @Inject
    PedidoStatusMapper pedidoStatusMapper;

    @Override
    @Transactional
    public void criar(PedidoStatus pedidoStatus) {
        try {
            PedidoStatusEntity pedidoStatusEntity = pedidoStatusMapper.toEntity(pedidoStatus);
            if (pedidoStatus.getIdPedido() != null) {
                PedidoEntity pedidoEntity = entityManager.find(PedidoEntity.class, pedidoStatus.getIdPedido());
                pedidoStatusEntity.setPedido(pedidoEntity);
            }

            entityManager.persist(pedidoStatusEntity);

        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public List<PedidoStatus> listarPorPedidoId(UUID pedidoId) {
        try {
            TypedQuery<PedidoStatusEntity> query = entityManager.createNamedQuery("PedidoStatus.findAllByPedidoId", PedidoStatusEntity.class);
            query.setParameter("pedido_id", pedidoId);

            return query.getResultList().stream()
                .map(pedidoStatusMapper::toDomain)
                .collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }
}
