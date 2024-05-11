package org.jfm.infra.repository.entities;

import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedidos")
@NamedQueries({
        @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM PedidoEntity p"),
        @NamedQuery(name = "Pedido.findById", query = "SELECT p FROM PedidoEntity p WHERE p.id = :id"),
        @NamedQuery(name = "Pedido.update", query = "UPDATE PedidoEntity p SET")
})
@Getter
@Setter
public class PedidoEntity {
    @Id
    private UUID id;
    private UUID idCliente;
    private Status status;
}
