package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.ports.PedidoRepository;

public class PedidoService {

    PedidoRepository pedidoRepository;

    // TODO: ver como funciona os agregados
    ItemService itemService;

    public PedidoService(PedidoRepository pedidoRepository, ItemService itemService) {
        this.pedidoRepository = pedidoRepository;
        this.itemService = itemService;
    }

    public UUID criar(Pedido pedido) {
        pedido.setId(UUID.randomUUID());
        pedidoRepository.criar(pedido);

        return pedido.getId();
    };

    public List<Pedido> listar() {
        return pedidoRepository.listar();
    };

    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.buscarPorId(id);
    };

    public void editar(Pedido pedido) {
        pedidoRepository.editar(pedido);
    };

}
