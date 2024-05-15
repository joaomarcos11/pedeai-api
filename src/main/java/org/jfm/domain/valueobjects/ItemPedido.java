package org.jfm.domain.valueobjects;

import java.util.UUID;

public class ItemPedido {
    private UUID idPedido;
    private UUID idItem;
    
    public ItemPedido(UUID idPedido, UUID idItem) {
        this.idPedido = idPedido;
        this.idItem = idItem;
    }

    public UUID getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(UUID idPedido) {
        this.idPedido = idPedido;
    }

    public UUID getIdItem() {
        return idItem;
    }

    public void setIdItem(UUID idItem) {
        this.idItem = idItem;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
        result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
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
        ItemPedido other = (ItemPedido) obj;
        if (idPedido == null) {
            if (other.idPedido != null)
                return false;
        } else if (!idPedido.equals(other.idPedido))
            return false;
        if (idItem == null) {
            if (other.idItem != null)
                return false;
        } else if (!idItem.equals(other.idItem))
            return false;
        return true;
    }
    
}
