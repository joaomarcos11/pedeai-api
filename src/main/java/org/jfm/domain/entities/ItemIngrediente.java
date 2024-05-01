package org.jfm.domain.entities;

public class ItemIngrediente {
    private int idItem;
    private int idIngrediente;
    
    public int getIdItem() {
        return idItem;
    }
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    public int getIdIngrediente() {
        return idIngrediente;
    }
    public void setIdIngrediente(int idIngrediente) {
        this.idIngrediente = idIngrediente;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idItem;
        result = prime * result + idIngrediente;
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
        ItemIngrediente other = (ItemIngrediente) obj;
        if (idItem != other.idItem)
            return false;
        if (idIngrediente != other.idIngrediente)
            return false;
        return true;
    }
};
