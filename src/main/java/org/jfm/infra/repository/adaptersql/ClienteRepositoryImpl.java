package org.jfm.infra.repository.adaptersql;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.ClienteRepository;
import org.jfm.infra.repository.adaptersql.entities.ClienteEntity;
import org.jfm.infra.repository.adaptersql.mapper.ClienteMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
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
        try {
            entityManager.persist(clienteMapper.toEntity(cliente));
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Cliente> listar() {
        try {
            return entityManager.createNamedQuery("Cliente.findAll", ClienteEntity.class)
                .getResultStream().map(c -> clienteMapper.toDomain(c)).collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente buscarPorId(UUID id) {
        try {
            TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.findById", ClienteEntity.class);
            query.setParameter("id", id);
    
            return clienteMapper.toDomain(query.getSingleResult());
        }  catch (NoResultException e) {
            throw new EntityNotFoundException(ErrosSistemaEnum.CLIENTE_NOT_FOUND.getMessage());
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente buscarPorCpf(String cpf) {
        try {
            TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.findByCpf", ClienteEntity.class);
            query.setParameter("cpf", cpf);

            return clienteMapper.toDomain(query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public Cliente buscarPorEmail(String email) {
        try {
            TypedQuery<ClienteEntity> query = entityManager.createNamedQuery("Cliente.findByEmail", ClienteEntity.class);
            query.setParameter("email", email);

            return clienteMapper.toDomain(query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void editar(Cliente cliente) {
        try {

            Query query = entityManager.createNamedQuery("Cliente.update");
            query.setParameter("id", cliente.getId());
            query.setParameter("nome", cliente.getNome());
            query.setParameter("cpf", cliente.getCpf());
            query.setParameter("email", cliente.getEmail());
            query.setParameter("ativo", cliente.getAtivo());
            
            query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public void remover(Cliente cliente) {
        try {

            Query query = entityManager.createNamedQuery("Cliente.delete");
            query.setParameter("id", cliente.getId());
            query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

}
