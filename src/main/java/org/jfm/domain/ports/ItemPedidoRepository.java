package org.jfm.domain.ports;

import java.util.List;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;

public interface ItemPedidoRepository {
    public void criar(Item item, Pedido pedido);

    public List<Item> listarPorPedido(Pedido pedido);

    public void remover(Item item, Pedido pedido);
};
