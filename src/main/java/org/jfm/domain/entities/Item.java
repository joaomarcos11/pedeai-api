package org.jfm.domain.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.exceptions.Exceptions.InvalidEntityException;
import org.jfm.domain.valueobjects.ItemPedido;

public class Item {
    private UUID id;
    private String nome;
    private int preco;
    private Categoria categoria;

    private Set<ItemPedido> itemPedidos = new HashSet<>();

    public Item() {
        super();
    }

    public Item(UUID id, String nome, int preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<ItemPedido> getItemPedidos() {
        return itemPedidos;
    }

    public List<Pedido> getPedidos() {
        return itemPedidos.stream().map(x -> x.getPedido()).toList();
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
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public void validar() {
        if (this.nome == null || this.nome.isBlank()) {
            throw new InvalidEntityException("Campo nome não pode ser vazio");
        }

        if (this.preco < 0) {
            throw new InvalidEntityException("Campo preço inválido");
        }

        // TODO - Pode criar Item sem Categoria vinculado?
    }

}
