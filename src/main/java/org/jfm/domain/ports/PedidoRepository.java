package org.jfm.domain.ports;

import org.jfm.domain.entities.Pedido;
import java.util.List;
import java.util.UUID;

public interface PedidoRepository {
    public void criar(Pedido pedido);

    public List<Pedido> listar();

    public Pedido buscarPorId(UUID id);

    public void editar(Pedido pedido);
};
