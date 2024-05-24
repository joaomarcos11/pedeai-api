package org.jfm.domain.valueobjects;

import java.io.Serializable;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;

public class ItemPedidoChave implements Serializable {
    private static final long serialVersionUID = 1L;

    private Item item;

    private Pedido pedido;

    public ItemPedidoChave() {
    }

    public ItemPedidoChave(Item item, Pedido pedido) {
        this.item = item;
        this.pedido = pedido;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
