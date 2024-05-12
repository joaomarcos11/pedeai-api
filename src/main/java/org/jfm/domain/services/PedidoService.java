package org.jfm.domain.services;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.usecases.PedidoUseCase;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public UUID criar(Pedido pedido) {
        pedido.setId(UUID.randomUUID());
        pedidoRepository.criar(pedido);

        return pedido.getId();
    };

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.listar();
    };

    @Override
    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.buscarPorId(id);
    };

    @Override
    public List<Pedido> listarPorStatus(Status status) {
        return pedidoRepository.listarPorStatus(status);
    }

    @Override
    public void editar(Pedido pedido) {
        pedidoRepository.editar(pedido);
    };

}
