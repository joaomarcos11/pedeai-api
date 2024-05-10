package org.jfm.domain.entities;

import java.util.UUID;

import org.jfm.domain.entities.enums.StatusPedido;

public class Pedido {
    private UUID id;
    private int idCliente;
    private StatusPedido status;

    public Pedido() {
        super();
    }

    public Pedido(UUID id, int idCliente, StatusPedido status) {
        this.id = id;
        this.idCliente = idCliente;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    @Override
    public int hashCode() {
        return this.id.hashCode(); // TODO: ver se não fiz caca
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
        if (!this.id.equals(other.id)) // TODO: ver se não fiz caca
            return false;
        return true;
    }

}
