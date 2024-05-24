package org.jfm.domain.valueobjects;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;

public class ItemPedido {

    private ItemPedidoChave id = new ItemPedidoChave();

    private int quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Item item, Pedido pedido, int quantidade) {
        id.setItem(item);
        id.setPedido(pedido);
        this.quantidade = quantidade;
    }

    public Item getItem() {
        return id.getItem();
    }

    public void setItem(Item item) {
        id.setItem(item);
    }

    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + quantidade;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemPedido other = (ItemPedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (quantidade != other.quantidade)
            return false;
        return true;
    }

}
