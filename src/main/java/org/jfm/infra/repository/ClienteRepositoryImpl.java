package org.jfm.infra.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.ports.ClienteRepository;
import org.jfm.infra.repository.entities.ClienteEntity;
import org.jfm.infra.repository.mapper.ClienteMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClienteRepositoryImpl implements ClienteRepository {
    
    @Inject
    EntityManager entityManager;
    
    @Inject
    ClienteMapper clienteMapper;
    
    @Override
    @Transactional
    public void criar(Cliente cliente) {
        entityManager.persist(clienteMapper.toDto(cliente));
    }

    @Override
    @Transactional
    public List<Cliente> listar() {
        return entityManager.createNamedQuery("Cliente.findAll", ClienteEntity.class)
        .getResultStream().map(p -> clienteMapper.toDomain(p)).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public Cliente buscarPorId(int id) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.findById", ClienteEntity.class);
        query.setParameter("id", id);
        
        return clienteMapper.toDomain(query.getSingleResult());
    }

    @Override
    @Transactional
    public Cliente buscarPorCpf(String cpf) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.findByCpf", ClienteEntity.class);
        query.setParameter("cpf", cpf);

        return clienteMapper.toDomain(query.getSingleResult());
    }

    @Override
    @Transactional
    public void editar(Cliente cliente) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.update", ClienteEntity.class);
        query.setParameter("id", cliente.getId());
        query.setParameter("nome", cliente.getNome());
        query.setParameter("cpf", cliente.getCpf());
        query.setParameter("email", cliente.getEmail());
        query.setParameter("dataAtualizacao", cliente.getDataAtualizacao());
        query.setParameter("ativo", cliente.getAtivo());

        // FIXME: provavelmente a query é diferente

        query.executeUpdate(); // TODO: ver isso aqui se é outro tipo de chamado ou conferir se foi executado;
        return;
    }

    @Override
    @Transactional
    public void remover(Cliente cliente) {
        entityManager.remove(clienteMapper.toDto(cliente)); // FIXME: dando erro ao remover
        return;
    }
    
}
