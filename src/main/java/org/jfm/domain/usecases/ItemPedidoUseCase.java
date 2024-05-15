package org.jfm.domain.usecases;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.valueobjects.ItemPedido;

public interface ItemPedidoUseCase {
    public void adicionarItemAoPedido(ItemPedido itemPedido);

    public List<Item> listarItensDoPedidoPeloId(UUID idPedido);

    public void removerItemDoPedido(ItemPedido itemPedido);
};
