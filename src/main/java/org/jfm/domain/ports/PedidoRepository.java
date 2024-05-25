package org.jfm.domain.ports;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PedidoRepository {
    public void criar(Pedido pedido);

    public List<Pedido> listar();

    public Pedido buscarPorId(UUID id);

    public List<Pedido> listarPorStatus(Status status);

    public void editar(Pedido pedido);

    public Map<Item, Integer> listarItensDoPedido(Pedido pedido);

    public void editarItensDoPedido(Pedido pedido);
};
