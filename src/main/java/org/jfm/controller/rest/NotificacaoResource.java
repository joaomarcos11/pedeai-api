package org.jfm.controller.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/notificacao/{tipo}/{id}")
@ApplicationScoped
public class NotificacaoResource {
    
    Map<String, Session> pagamentoSessions = new ConcurrentHashMap<>();
    Map<String, Session> clienteSessions = new ConcurrentHashMap<>(); 
    Map<String, Session> cozinhaSessions = new ConcurrentHashMap<>(); 

    @OnOpen
    public void onOpen(Session session, @PathParam("tipo") String tipo, @PathParam("id") String id) {
        // TODO: logar conexão?
        switch (tipo) {
            case "pagamento":
            pagamentoSessions.put(id, session);
            break;
            
            case "cliente":
            clienteSessions.put(id, session);
            break;
            
            case "cozinha":
            cozinhaSessions.put(id, session);
            break;
            
            default:
                // TODO: logar erro?
                break;
        }
    }

    @OnClose
    public void onClose(Session session, @PathParam("tipo") String tipo, @PathParam("id") String id) {
        // TODO: logar disconexão?
        switch (tipo) {
            case "pagamento":
            pagamentoSessions.put(id, session);
            break;

            case "cliente":
            clienteSessions.remove(id, session);
            break;
            
            case "cozinha":
            cozinhaSessions.remove(id, session);
            break;
            
            default:
                // TODO: logar erro?
                break;
        }
    }

    @OnError
    public void onError(Session session, @PathParam("tipo") String tipo, @PathParam("id") String id, Throwable throwable) {
        // TODO: logar erro?
        switch (tipo) {
            case "pagamento":
            pagamentoSessions.put(id, session);
            break;

            case "cliente":
            clienteSessions.remove(id, session);
            break;
            
            case "cozinha":
            cozinhaSessions.remove(id, session);
            break;

            default:
                // TODO: logar erro?
                break;
        }
    }

    public void notificarPagamento(String pedidoId, String mensagem) {
        this.notificar(pagamentoSessions.get(pedidoId), mensagem);
    }
    
    public void notificarClientes(String mensagem) {
        clienteSessions.values().forEach(s -> this.notificar(s, mensagem));
    }

    public void notificarCozinha(String mensagem) {
        cozinhaSessions.values().forEach(s -> this.notificar(s, mensagem));
    }

    private void notificar(Session session, String mensagem) {
        session.getAsyncRemote().sendObject(mensagem, result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

}
