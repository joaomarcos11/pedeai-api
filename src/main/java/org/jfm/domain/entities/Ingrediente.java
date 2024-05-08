package org.jfm.domain.entities;

import java.util.Objects;
import org.jfm.domain.entities.enums.Categoria;

public class Ingrediente {
    private int id;
    private String descricao;
    private int preco;
    private boolean vegetariano;
    private boolean vegano;
    private boolean adicional;

    private Categoria categoria;

    public Ingrediente() {
    };

    public Ingrediente(int id, String descricao, int preco, boolean vegetariano, boolean vegano, boolean adicional,
            Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.vegetariano = vegetariano;
        this.vegano = vegano;
        this.adicional = adicional;
        this.categoria = categoria;
    };

    public int getId() {
        return id;
    };

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    };

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public boolean getVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(boolean vegetariano) {
        this.vegetariano = vegetariano;
    }

    public boolean getVegano() {
        return vegano;
    }

    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }

    public boolean getAdicional() {
        return adicional;
    }

    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingrediente other = (Ingrediente) obj;
        return Objects.equals(id, other.id);
    }
}
