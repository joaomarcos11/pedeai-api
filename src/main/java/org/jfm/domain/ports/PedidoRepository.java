package org.jfm.domain.ports;

import org.jfm.domain.entities.Pedido;
import java.util.List;

public interface PedidoRepository {
    public void criar(Pedido pedido);

    public List<Pedido> listar();

    public Pedido buscarPorId(int id);

    public void editar(Pedido pedido);
};
