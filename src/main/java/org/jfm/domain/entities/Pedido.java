package org.jfm.domain.entities;

import java.util.Map;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

public class Pedido {
    private UUID id;
    private UUID idCliente;
    private Status status;
    private Map<Item, Integer> itens; // TODO: trocar por Map<UUID, Integer> ???

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

    public Map<Item, Integer> getItens() {
        return this.itens;
    }

    public void setItens(Map<Item, Integer> itens) {
        this.itens = itens;
    }

    // public void addItem(Item item, int quantidade) {
    //     if (this.itens.containsKey(item)) {
    //         this.itens.put(item, this.itens.get(item) + quantidade);
    //         return;
    //     }
    //     this.itens.put(item, quantidade);
    // }

    // public void removeItem(Item item, int quantidade) {
    //     if (!this.itens.containsKey(item)) {
    //         throw new EntityNotFoundException("item não faz parte do pedido"); // TODO: talvez alterar
    //     }

    //     if (this.itens.get(item) < quantidade) {
    //         throw new EntityNotFoundException("quantidade insuficiente"); // FIXME: arrumar esse throw aqui
    //     }

    //     this.itens.put(item, this.itens.get(item) - quantidade);
    // }

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
