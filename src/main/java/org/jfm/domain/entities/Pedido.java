package org.jfm.domain.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.EntityInvalidException;
import org.jfm.domain.valueobjects.ItemPedido;

public class Pedido {
    private UUID id;
    private UUID idCliente;
    private Status status;
    private Set<ItemPedido> itens = new HashSet<>();
    private Instant dataCriacao;

    public Pedido() {
    }

    public Pedido(UUID id, UUID idCliente, Status status) {
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

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<ItemPedido> getItens() {
        return this.itens;
    }

    public Instant getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void validar() {
        if (this.itens == null) {
            throw new EntityInvalidException("pedido deve conter itens");
        }
    }

    public List<Item> getItem() {
        return itens.stream().map(x -> x.getItem()).toList();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
