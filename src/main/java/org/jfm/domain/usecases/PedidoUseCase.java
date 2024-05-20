package org.jfm.domain.usecases;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;

public interface PedidoUseCase {
    public UUID criar(Pedido pedido);

    public List<Pedido> listar();

    public Pedido buscarPorId(UUID id);

    public List<Pedido> listarPorStatus(Status status);

    public void editar(Pedido pedido);

    public boolean pagar(UUID id);

    // public void adicionarItemAoPedido(UUID idPedido, UUID idItem, int quantidade);

    // public Map<Item, Integer> listarItensDoPedidoPeloId(UUID idPedido);

    // public void removerItemDoPedido(UUID idPedido, UUID idItem, int quantidade);

    public void editarItensDoPedido(UUID idPedido, Map<UUID, Integer> itemQuantidade);
}
