package org.jfm.domain.ports;

import java.util.UUID;

public interface Notificacao {
    public void notificacaoPagamento(UUID pedidoId, String mensagem);
    public void notificarClientes(UUID data);
    public void notificarCozinha(UUID data);
}
