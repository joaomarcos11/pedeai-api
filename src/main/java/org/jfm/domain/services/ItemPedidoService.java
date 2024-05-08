package org.jfm.domain.services;

import java.util.List;

import org.jfm.domain.entities.ItemPedido;
import org.jfm.domain.ports.ItemPedidoRepository;

public class ItemPedidoService {

    ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    };

    public void criar(ItemPedido itemPedido) {
        itemPedidoRepository.criar(itemPedido);
    }

    public List<ItemPedido> listar() {
        return itemPedidoRepository.listar();
    }

    public List<ItemPedido> buscarPorPedidoId(int pedidoId) {
        return itemPedidoRepository.buscarPorPedidoId(pedidoId);
    }

    public ItemPedido buscarPorPedidoIdItemId(int peididoId, int itemId) {
        return itemPedidoRepository.buscarPorPedidoIdItemId(peididoId, itemId);
    }

    public void remover(ItemPedido itemPedido) {
        itemPedidoRepository.remover(itemPedido);
    }
}
