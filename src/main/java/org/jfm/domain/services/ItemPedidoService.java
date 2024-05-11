package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.ports.ItemPedidoRepository;
import org.jfm.domain.usecases.ItemPedidoUseCase;
import org.jfm.domain.valueObjects.ItemPedido;

public class ItemPedidoService implements ItemPedidoUseCase {

    ItemPedidoRepository itemPedidoRepository;

    PedidoService pedidoService;

    ItemService itemService;
    
    public ItemPedidoService(PedidoService pedidoService, ItemService itemService) {
        this.pedidoService = pedidoService;
        this.itemService = itemService;
    }

    @Override
    public void adicionarItemAoPedido(ItemPedido itemPedido) {
        this.itemPedidoRepository.criar(itemPedido);
    };

    @Override
    public List<Item> listarItensDoPedido(UUID idPedido) {
        return this.itemPedidoRepository.listarPorPedidoId(idPedido);
    }

    @Override
    public void removerItemDoPedido(ItemPedido itemPedido) {
        this.itemPedidoRepository.remover(itemPedido);
    }

}
