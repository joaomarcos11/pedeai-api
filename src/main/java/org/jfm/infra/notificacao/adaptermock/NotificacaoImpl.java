package org.jfm.infra.notificacao.adaptermock;

import java.util.UUID;

import org.jfm.controller.rest.NotificacaoResource;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.ports.Notificacao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificacaoImpl implements Notificacao {

    @Inject
    NotificacaoResource resource;

    @Override
    public void notificacaoPagamento(UUID pedidoId, Status status) {
        this.resource.notificarPagamento(pedidoId, status);
    }

    @Override
    public void notificarClientes(UUID data) {
        this.resource.notificarClientes(data);
    }

    @Override
    public void notificarCozinha(UUID data) {
        this.resource.notificarCozinha(data);
    }
    
}
