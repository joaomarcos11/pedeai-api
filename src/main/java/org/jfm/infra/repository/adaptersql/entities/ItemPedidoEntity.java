package org.jfm.infra.repository.adaptersql.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens_pedidos")
@NamedQueries({
        @NamedQuery(name = "ItemPedido.findByPedidoId", query = "SELECT i FROM ItemPedidoEntity i WHERE i.id.pedido = :pedido_id")
})
public class ItemPedidoEntity {

    @EmbeddedId
    private ItemPedidoKey id = new ItemPedidoKey();

    private int quantidade;

    public ItemPedidoEntity() {
    }

    public ItemPedidoEntity(ItemEntity item, PedidoEntity pedido, int quantidade) {
        id.setItem(item);
        id.setPedido(pedido);
        this.quantidade = quantidade;
    }

    public ItemEntity getItem() {
        return id.getItem();
    }

    public void setItem(ItemEntity item) {
        id.setItem(item);
    }

    public PedidoEntity getPedido() {
        return id.getPedido();
    }

    public void setPedido(PedidoEntity pedido) {
        id.setPedido(pedido);
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}