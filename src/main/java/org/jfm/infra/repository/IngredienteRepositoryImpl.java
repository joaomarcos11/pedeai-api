package org.jfm.infra.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Ingrediente;
import org.jfm.domain.ports.IngredienteRepository;
import org.jfm.infra.repository.entities.IngredienteEntity;
import org.jfm.infra.repository.mapper.IngredienteMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class IngredienteRepositoryImpl implements IngredienteRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    IngredienteMapper ingredienteMapper;

    @Override
    @Transactional
    public void criar(Ingrediente ingrediente) {
        entityManager.persist(ingredienteMapper.toEntity(ingrediente));
    }

    @Override
    @Transactional
    public List<Ingrediente> listar() {
        return entityManager.createNamedQuery("Ingrediente.findAll", IngredienteEntity.class)
                .getResultStream().map(i -> ingredienteMapper.toDomain(i)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Ingrediente buscarPorId(int id) {
        TypedQuery<IngredienteEntity> query = entityManager.createNamedQuery("Ingrediente.findById",
                IngredienteEntity.class);
        query.setParameter("id", id);

        return ingredienteMapper.toDomain(query.getSingleResult());
    }

    @Override
    @Transactional
    public void editar(Ingrediente ingrediente) {
        Query query = entityManager.createNamedQuery("Ingrediente.update");
        query.setParameter("id", ingrediente.getId());
        query.setParameter("descricao", ingrediente.getDescricao());
        query.setParameter("preco", ingrediente.getPreco());
        query.setParameter("vegetariano", ingrediente.getVegetariano());
        query.setParameter("vegano", ingrediente.getVegano());
        query.setParameter("adicional", ingrediente.getAdicional());

        query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
    }

    @Override
    @Transactional
    public void remover(Ingrediente ingrediente) {
        Query query = entityManager.createNamedQuery("Ingrediente.delete");
        query.setParameter("id", ingrediente.getId());
        query.executeUpdate(); // TODO: utilizar o return para verificar alguma coisa?
    }

}
