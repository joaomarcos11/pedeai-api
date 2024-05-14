package org.jfm.infra.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.infra.repository.entities.PedidoEntity;
import org.jfm.infra.repository.mapper.PedidoMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoRepositoryImpl implements PedidoRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    PedidoMapper pedidoMapper;

    @Override
    @Transactional
    public void criar(Pedido pedido) {
        entityManager.persist(pedidoMapper.toEntity(pedido));
    }

    @Override
    @Transactional
    public List<Pedido> listar() {
        return entityManager.createNamedQuery("Pedido.findAll", PedidoEntity.class).getResultStream()
                .map(p -> pedidoMapper.toDomain(p)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Pedido buscarPorId(UUID id) {
        TypedQuery<PedidoEntity> query = entityManager.createNamedQuery("Pedido.findById", PedidoEntity.class);
        query.setParameter("id", id);

        return pedidoMapper.toDomain(query.getSingleResult());
    }

    @Override
    @Transactional
    public List<Pedido> listarPorStatus(Status status) {
        TypedQuery<PedidoEntity> query = entityManager.createNamedQuery("Pedido.findByStatus", PedidoEntity.class);
        query.setParameter("status", status);

        return query.getResultStream().map(p -> pedidoMapper.toDomain(p)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void editar(Pedido pedido) {
        Query query = entityManager.createNamedQuery("Pedido.update");
        query.setParameter("id", pedido.getId());
        // query.setParameter("idCliente", pedido.getIdCliente());
        query.setParameter("status", pedido.getStatus());

        query.executeUpdate();
    }

}
