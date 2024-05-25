package org.jfm.infra.repository.adaptersql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.infra.repository.adaptersql.entities.ClienteEntity;
import org.jfm.infra.repository.adaptersql.entities.ItemEntity;
import org.jfm.infra.repository.adaptersql.entities.ItemPedidoEntity;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.jfm.infra.repository.adaptersql.mapper.ItemMapper;
import org.jfm.infra.repository.adaptersql.mapper.PedidoMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PedidoRepositoryImpl implements PedidoRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    PedidoMapper pedidoMapper;

    @Inject
    ItemMapper itemMapper;

    @Override
    @Transactional
    public void criar(Pedido pedido) {
        try {
            PedidoEntity pedidoEntity = pedidoMapper.toEntity(pedido, pedido.getId());
            if (pedido.getIdCliente() != null) {
                ClienteEntity clienteEntity = entityManager.find(ClienteEntity.class, pedido.getIdCliente());
                pedidoEntity.setCliente(clienteEntity);
            }
            entityManager.persist(pedidoEntity);
        } catch (PersistenceException e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Pedido> listar() {
        try {
            // List<PedidoEntity> pedidos = entityManager.createNamedQuery("Pedido.findAll", PedidoEntity.class).getResultStream().collect(Collectors.toList());
            // for (PedidoEntity pedido : pedidos) {
            //     System.out.println("Pedido id: " + pedido.getId().toString());
            //     for (ItemPedidoEntity item : pedido.getItens()) {
            //         System.out.println("item pedido id: " + item.getId());
            //         System.out.println("item pedido qt: " + item.getQuantidade());
            //         System.out.println("item pedido item id: " + item.getItem().getId());
            //         System.out.println("item pedido item nome: " + item.getItem().getNome());
            //         System.out.println("item pedido item preco: " + item.getItem().getNome());
            //     }
            // }
            // retorna os itens certfinha ^

            return entityManager.createNamedQuery("Pedido.findAll", PedidoEntity.class).getResultStream().map(p -> pedidoMapper.toDomain(p)).collect(Collectors.toList());
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
    
            return pedidoMapper.toDomain(query.getSingleResult()); // TODO: trocar o mapper
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
            
            return query.getResultStream().map(p -> pedidoMapper.toDomain(p)).collect(Collectors.toList()); // TODO: trocar o mapper
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

    // @Override
    // @Transactional
    // public void adicionarItemAoPedido(Item item, Pedido pedido, int quantidade) {
    //     try {
    //         ItemEntity itemEntity = entityManager.find(ItemEntity.class, item.getId());
    //         PedidoEntity pedidoEntity = entityManager.find(PedidoEntity.class, pedido.getId());

    //         ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity();
    //         itemPedidoEntity.setItem(itemEntity);
    //         itemPedidoEntity.setPedido(pedidoEntity);
    //         itemPedidoEntity.setQuantidade(quantidade); 

    //         entityManager.persist(itemPedidoEntity);
    //     } catch (PersistenceException e) {
    //         throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage());
    //     }
    // }

    @Override
    @Transactional
    public Map<Item, Integer> listarItensDoPedido(Pedido pedido) {
        try {
            TypedQuery<PedidoEntity> query = entityManager.createNamedQuery("Pedido.findById", PedidoEntity.class);
            query.setParameter("id", pedido.getId());

            PedidoEntity pedidoEntity = query.getSingleResult();

            Set<ItemPedidoEntity> itensPedidos = pedidoEntity.getItens();

            Map<Item, Integer> itens = new HashMap<>();

            itensPedidos.forEach(i -> itens.put(itemMapper.toDomain(i.getItem()), Integer.valueOf(i.getQuantidade())));

            // return pedidoEntity.getItemPedidos().stream().collect(Collectors.toMap(itemMapper.toDomain(ItemPedidoEntity::getItem), ItemPedidoEntity::getQuantidade));

            return itens;

        } catch (Exception e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage()); // TODO: trocar essa exception aqui
        }
    }

    @Override
    @Transactional
    public void editarItensDoPedido(Pedido pedido) {
        try {
            entityManager.persist(pedido);
        } catch (Exception e) {
            throw new ErrorSqlException(ErrosSistemaEnum.DATABASE_ERROR.getMessage()); // TODO: trocar essa exception aqui
        }
    }

    // @Override
    // @Transactional
    // public void removerItemDoPedido(Item item, Pedido pedido, int quantidade) {
    //     try {

    //     } catch() {

    //     }
    // }

}
