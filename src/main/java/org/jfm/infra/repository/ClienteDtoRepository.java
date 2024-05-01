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

@ApplicationScoped
public class ClienteDtoRepository implements ClienteRepository {
    
    @Inject
    EntityManager entityManager;
    
    ClienteMapper clienteMapper;
    
    @Override
    public Cliente criar(Cliente cliente) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.create", ClienteEntity.class);
        query.setParameter("id", cliente.getId());
        query.setParameter("nome", cliente.getNome());
        query.setParameter("cpf", cliente.getCpf());
        query.setParameter("email", cliente.getEmail());
        query.setParameter("dataCriacao", cliente.getDataCriacao());
        query.setParameter("dataAtualizacao", cliente.getDataAtualizacao());
        query.setParameter("ativo", cliente.getAtivo());
        
        return clienteMapper.toDomain(query.getSingleResult());
    }

    @Override
    public List<Cliente> listar() {
        return entityManager.createNamedQuery("Cliente.findAll", ClienteEntity.class)
        .getResultStream().map(p -> clienteMapper.toDomain(p)).collect(Collectors.toList());
    }
    
    @Override
    public Cliente buscarPorId(int id) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.findById", ClienteEntity.class);
        query.setParameter("id", id);
        
        return clienteMapper.toDomain(query.getSingleResult());
    }

    @Override
    public Cliente buscarPorCpf(String cpf) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.findByCpf", ClienteEntity.class);
        query.setParameter("cpf", cpf);

        return clienteMapper.toDomain(query.getSingleResult());
    }

    @Override
    public void editar(Cliente cliente) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.update", ClienteEntity.class);
        query.setParameter("id", cliente.getId());
        query.setParameter("nome", cliente.getNome());
        query.setParameter("cpf", cliente.getCpf());
        query.setParameter("email", cliente.getEmail());
        query.setParameter("dataAtualizacao", cliente.getDataAtualizacao());
        query.setParameter("ativo", cliente.getAtivo());

        query.executeUpdate(); // TODO: ver isso aqui se é outro tipo de chamado ou conferir se foi executado;
        return;
    }

    @Override
    public void remover(int id) {
        TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.delete", ClienteEntity.class);
        query.setParameter("id", id);
        
        query.executeUpdate();
        return;
    }
    
}
