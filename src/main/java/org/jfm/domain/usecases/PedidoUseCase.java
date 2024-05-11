package org.jfm.domain.usecases;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;

public interface PedidoUseCase {
    public UUID criar(Pedido pedido);

    public List<Pedido> listar();

    public Pedido buscarPorId(UUID id);

    public void editar(Pedido pedido);
}
