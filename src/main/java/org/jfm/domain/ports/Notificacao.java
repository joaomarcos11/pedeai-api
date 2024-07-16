package org.jfm.domain.ports;

public interface Notificacao {
    public void notificacaoPagamento(String pedidoId, String mensagem);
    public void notificarClientes(String data);
    public void notificarCozinha(String data);
}
