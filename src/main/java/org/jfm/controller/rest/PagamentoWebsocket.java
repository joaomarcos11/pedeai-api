package org.jfm.controller.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/pagamento/ws/{id}")         
@ApplicationScoped
public class PagamentoWebsocket {
    Map<String, Session> sessions = new ConcurrentHashMap<>(); 

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        broadcast("User " + id + " joined");
        sessions.put(id, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("id") String id) {
        sessions.remove(id);
        broadcast("User " + id + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("id") String id, Throwable throwable) {
        sessions.remove(id);
        broadcast("User " + id + " left on error: " + throwable);
    }

    // @OnMessage
    // public void onMessage(String message, @PathParam("id") String id) {
    //     broadcast(">> " + id + ": " + message);
    // }

    @OnMessage
    public void onMessage(String message, @PathParam("id") String id) {
        sessions.get(id).getAsyncRemote().sendObject("PAGO", result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    public void sendMessage(String id) {
        sessions.get(id).getAsyncRemote().sendObject("PAGO", result -> {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
    }

    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result ->  {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }
}
