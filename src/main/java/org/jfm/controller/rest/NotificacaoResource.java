package org.jfm.controller.rest;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.jfm.domain.entities.enums.Status;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/notificacao/{id}")
@ApplicationScoped
public class NotificacaoResource {
    
    Map<String, Session> pagamentoSessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        pagamentoSessions.put(id, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        pagamentoSessions.put(id, session);
    }

    @OnError
    public void onError(Session session, @PathParam("id") String id, Throwable throwable) {
        pagamentoSessions.put(id, session);
    }

    public void notificarPagamento(UUID pedidoId, Status status) {
        this.notificar(pagamentoSessions.get(pedidoId.toString()), status.toString());
    }

    private void notificar(Session session, String mensagem) {
        session.getAsyncRemote().sendObject(mensagem, result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

}
