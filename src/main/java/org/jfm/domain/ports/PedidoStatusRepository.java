package org.jfm.domain.ports;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.PedidoStatus;

public interface PedidoStatusRepository {
    public void criar(PedidoStatus pedidoStatus);
    public List<PedidoStatus> listarPorPedidoId(UUID id);
}
