package org.jfm.domain.usecases;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.valueobjects.Pagamento;

public interface PedidoUseCase {
    public Pagamento criar(Pedido pedido);

    public List<Pedido> listar();

    public List<Pedido> listarEmAndamento();

    public Pedido buscarPorId(UUID id);

    public List<Pedido> listarPorStatus(Status status);

    public void editar(Pedido pedido);

    public List<PedidoStatus> buscarHistoricoStatus(UUID id);

    public boolean estaPago(UUID id);

    public void pagamentoPedido(UUID id, Status status);
}
