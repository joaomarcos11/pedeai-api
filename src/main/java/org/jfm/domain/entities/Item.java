package org.jfm.domain.entities;

import java.util.UUID;

import org.jfm.domain.entities.enums.Categoria;

public class Item {
    private UUID id;
    private String nome;
    private int preco;
    private Categoria idCategoria;

    public Item() {
        super();
    }

    public Item(UUID id, String nome, int preco, Categoria idCategoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.idCategoria = idCategoria;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
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
        Item other = (Item) obj;
        if (!this.id.equals(other.id)) // TODO: ver se não fiz caca
            return false;
        return true;
    }

}
