package org.jfm.infra.repository.adaptersql.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pedidos")
@NamedQueries({
        @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM PedidoEntity p"),
        @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM PedidoEntity p WHERE p.id = :id"),
        @NamedQuery(name = "Pedido.findByStatus", query = "SELECT p FROM PedidoEntity p WHERE p.status = :status"),
        @NamedQuery(name = "Pedido.update", query = "UPDATE PedidoEntity p SET p.status = :status WHERE p.id = :id"),
})
@Data
public class PedidoEntity {

    @Id
    private UUID id;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedidoEntity> itens = new HashSet<>();

    public PedidoEntity() {
    }

    public PedidoEntity(ClienteEntity cliente, UUID id, Status status) {
        this.cliente = cliente;
        this.id = id;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public Set<ItemPedidoEntity> getItens() {
        return itens;
    }

    public List<ItemEntity> getItem() {
        return itens.stream().map(x -> x.getItem()).toList();
    }

}
