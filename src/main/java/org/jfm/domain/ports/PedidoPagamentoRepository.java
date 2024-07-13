package org.jfm.domain.ports;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;

public interface PedidoPagamentoRepository {
    public void criar(Pedido pedido, UUID pagamentoId, Instant dataCriacao);

    public Map<UUID, Instant> buscarPorPedidosId(UUID pedidoId);
}
