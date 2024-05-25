package org.jfm.infra.repository.adaptersql.entities;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class ItemPedidoKey implements Serializable {

    @Column(name = "pedido_id")
    UUID pedidoId;

    @Column(name = "item_id")
    UUID itemId;
        
}
