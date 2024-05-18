package org.jfm.domain.usecases;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;

public interface PedidoUseCase {
    public UUID criar(Pedido pedido);

    public List<Pedido> listar();

    public Pedido buscarPorId(UUID id);

    public List<Pedido> listarPorStatus(Status status);

    public void editar(Pedido pedido);

    public boolean pagar(UUID id);

    public void adicionarItemAoPedido(UUID idPedido, UUID idItem, int quantidade);

    public List<Item> listarItensDoPedidoPeloId(UUID idPedido);

    public void removerItemDoPedido(UUID idPedido, UUID idItem, int quantidade);
}
