package org.jfm.infra.repository.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ItemPedidoKey {
    
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    public ItemPedidoKey() {
    }

    public ItemPedidoKey(PedidoEntity pedido, ItemEntity item) {
        this.pedido = pedido;
        this.item = item;
    }
    
}
