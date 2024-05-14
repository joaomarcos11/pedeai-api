package org.jfm.infra.repository.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.EmbeddedId;

@Entity
@Table(name = "itens_pedidos")
@Getter
@Setter
public class ItemPedidoEntity {

    @EmbeddedId
    private ItemPedidoKey id = new ItemPedidoKey();

    private Integer quantity;

    public ItemPedidoEntity () {
    }

    public ItemPedidoEntity(ItemEntity item, PedidoEntity pedido, Integer quantity) {
        id.setItem(item);
        id.setPedido(pedido);
        this.quantity = quantity;
    }

}
