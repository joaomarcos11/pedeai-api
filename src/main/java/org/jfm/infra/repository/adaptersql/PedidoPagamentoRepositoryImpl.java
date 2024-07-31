package org.jfm.infra.repository.adaptersql;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.infra.repository.adaptersql.entities.PedidoPagamentoEntity;
import org.jfm.infra.repository.adaptersql.entities.PedidoPagamentoKey;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class PedidoPagamentoRepositoryImpl implements PedidoPagamentoRepository {
    
    @Inject
    EntityManager entityManager;

    @Override
    public void criar(Pedido pedido, UUID pagamentoId, Instant dataCriacao) {
        try {
            PedidoPagamentoEntity pedidoPagamentoEntity = new PedidoPagamentoEntity();
            PedidoPagamentoKey pedidoPagamentoKey = new PedidoPagamentoKey();
            pedidoPagamentoKey.setPedidoId(pedido.getId());
            pedidoPagamentoKey.setPagamentoId(pagamentoId);
            pedidoPagamentoEntity.setId(pedidoPagamentoKey);
            pedidoPagamentoEntity.setDataCriacao(dataCriacao);
            entityManager.persist(pedidoPagamentoEntity);
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    public Map<UUID, Instant> buscarPorPedidosId(UUID pedidoId) {
        try {
            TypedQuery<PedidoPagamentoEntity> query = entityManager.createNamedQuery("PedidoPagamento.findByPedidoId", PedidoPagamentoEntity.class);
            query.setParameter("pedido_id", pedidoId);

            List<PedidoPagamentoEntity> pedidosPagamentosResult = query.getResultList();

            Map<UUID, Instant> pedidosPagamentos = new HashMap<>();

            pedidosPagamentosResult.forEach(p -> pedidosPagamentos.put(p.getId().getPagamentoId(), p.getDataCriacao()));

            return pedidosPagamentos;
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }



}
