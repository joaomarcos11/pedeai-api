package org.jfm.infra.notificacao.adaptermock;

import org.jfm.controller.rest.NotificacaoResource;
import org.jfm.domain.ports.Notificacao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificacaoImpl implements Notificacao {

    @Inject
    NotificacaoResource resource;

    @Override
    public void notificacaoPagamento(String pedidoId, String mensagem) {
        this.resource.notificarPagamento(pedidoId, mensagem);
    }

    @Override
    public void notificarClientes(String data) {
        this.resource.notificarClientes(data);
    }

    @Override
    public void notificarCozinha(String data) {
        this.resource.notificarCozinha(data);
    }
    
}
