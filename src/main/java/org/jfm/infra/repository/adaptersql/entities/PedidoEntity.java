package org.jfm.infra.repository.adaptersql.entities;

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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@NamedQueries({
        @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM PedidoEntity p"),
        @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM PedidoEntity p WHERE p.id = :id"),
        @NamedQuery(name = "Pedido.findByStatus", query = "SELECT p FROM PedidoEntity p WHERE p.status = :status"),
        @NamedQuery(name = "Pedido.update", query = "UPDATE PedidoEntity p SET p.status = :status WHERE p.id = :id"),
})
@Getter
@Setter
public class PedidoEntity {
    @Id
    private UUID id;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "id.pedido")
    Set<ItemPedidoEntity> itens;

}
