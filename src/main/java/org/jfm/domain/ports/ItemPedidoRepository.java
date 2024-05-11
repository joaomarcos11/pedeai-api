package org.jfm.domain.ports;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.valueObjects.ItemPedido;

public interface ItemPedidoRepository {
    public void criar(ItemPedido itemPedido);

    public List<Item> listarPorPedidoId(UUID idPedido);

    public void remover(ItemPedido itemPedido);
};
