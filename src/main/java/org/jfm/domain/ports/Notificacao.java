package org.jfm.domain.ports;

import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

public interface Notificacao {
    public void notificacaoPagamento(UUID pedidoId, Status status);
}
