package org.jfm.infra.repository.adaptersql.entities;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ItemPedidoKey implements Serializable {

    @Column(name = "pedido_id")
    UUID pedidoId;

    @Column(name = "item_id")
    UUID itemId;

    // private static final long serialVersionUID = 1L;

    // @ManyToOne
    // @MapsId("pedidoId")
    // @JoinColumn(name = "pedido_id")
    // private PedidoEntity pedido;

    // @ManyToOne
    // @MapsId("itemId")
    // @JoinColumn(name = "item_id")
    // private ItemEntity item;

    // public ItemPedidoKey() {
    // }

    // public ItemPedidoKey(PedidoEntity pedido, ItemEntity item) {
    //     this.pedido = pedido;
    //     this.item = item;
    // }
    
}
