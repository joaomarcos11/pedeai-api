package org.jfm.domain.ports;

import java.util.List;

import org.jfm.domain.entities.ItemPedido;

public interface ItemPedidoRepository {
    public void criar(ItemPedido itemPedido);

    public List<ItemPedido> listar();

    public List<ItemPedido> buscarPorPedidoId(int pedidoId);
    
    // FIXME: avaliar as buscar, pode ter o mesmo item no mesmo pedido????
    public ItemPedido buscarPorPedidoIdItemId(int peididoId, int itemId);

    public void remover(ItemPedido itemPedido);
    
}
