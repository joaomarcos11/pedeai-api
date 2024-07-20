package org.jfm.infra.repository.adaptersql.entities;

import java.time.Instant;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "pedidos_status")
@NamedQueries({
    @NamedQuery(name = "PedidoStatus.findAllByPedidoId", query = "SELECT ps FROM PedidoStatusEntity ps WHERE ps.pedido.id = :pedido_id")
})
@Getter
@Setter
public class PedidoStatusEntity {
    @Id
    private UUID id;
    //TODO: Column?
    private Status anterior;
    private Status atual;

    @Column(name = "data")
    private Instant data;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;
}
