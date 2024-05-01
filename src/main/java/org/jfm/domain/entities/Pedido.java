package org.jfm.domain.entities;

import java.time.Instant;

import org.jfm.domain.enums.StatusPedido;

public class Pedido {
    private int id;
    private int idCliente;
    private StatusPedido status;
    private Instant iniciado;
    private Instant concluido;
    private int preco;
    private boolean delivery;

    public Pedido() {
        super();
    }

    public Pedido(int id, int idCliente, StatusPedido status, Instant iniciado, Instant concluido, int preco,
            boolean delivery) {
        this.id = id;
        this.idCliente = idCliente;
        this.status = status;
        this.iniciado = iniciado;
        this.concluido = concluido;
        this.preco = preco;
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Instant getIniciado() {
        return iniciado;
    }

    public void setIniciado(Instant iniciado) {
        this.iniciado = iniciado;
    }

    public Instant getConcluido() {
        return concluido;
    }

    public void setConcluido(Instant concluido) {
        this.concluido = concluido;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pedido other = (Pedido) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
