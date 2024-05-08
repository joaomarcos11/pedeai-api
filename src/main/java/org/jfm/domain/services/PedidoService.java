package org.jfm.domain.services;

import java.util.List;
import java.util.Random;

import org.jfm.domain.entities.ItemPedido;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.ports.PedidoRepository;

public class PedidoService {

    PedidoRepository pedidoRepository;

    // TODO: adicionei isso aqui, ver como funciona os agregados...
    ItemPedidoService itemPedidoService;

    // TODO: adicionei isso aqui, ver como funciona os agregados...
    ItemService itemService;

    public PedidoService(PedidoRepository pedidoRepository, ItemPedidoService itemPedidoService, ItemService itemService) {
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoService = itemPedidoService;
        this.itemService = itemService;
    }

    public int criar(Pedido pedido) {
        Random rand = new Random();

        pedido.setId(rand.nextInt());
        pedidoRepository.criar(pedido);

        return pedido.getId();
    };

    public List<Pedido> listar() {
        return pedidoRepository.listar();
    };

    public Pedido buscarPorId(int id) {
        return pedidoRepository.buscarPorId(id);
    };

    public void editar(Pedido pedido) {
        pedidoRepository.editar(pedido);
    };

    // ---

    // listar itens pedido relacionados
    public List<ItemPedido> listarItemPedidoRelacionado(Pedido pedido) {
        return itemPedidoService.buscarPorPedidoId(pedido.getId());
    }

}
