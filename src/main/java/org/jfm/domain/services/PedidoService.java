package org.jfm.domain.services;

import java.util.List;
import java.util.Random;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.ports.PedidoRepository;

public class PedidoService {

    PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
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

}
