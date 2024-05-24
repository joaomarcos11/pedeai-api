package org.jfm.infra.repository.adaptersql.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.jfm.domain.entities.enums.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "itens")
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM ItemEntity i"),
        @NamedQuery(name = "Item.findById", query = "SELECT i FROM ItemEntity i WHERE i.id = :id"),
        @NamedQuery(name = "Item.update", query = "UPDATE ItemEntity i SET i.nome = :nome, i.preco = :preco, i.categoria = :categoria WHERE i.id = :id"),
        @NamedQuery(name = "Item.delete", query = "DELETE FROM ItemEntity i WHERE i.id = :id"),
        @NamedQuery(name = "Item.findByCategoria", query = "SELECT i FROM ItemEntity i WHERE i.categoria = :categoria"),
})
public class ItemEntity {

    @Id
    private UUID id;

    private String nome;
    private int preco;

    private Categoria categoria;

    @OneToMany(mappedBy = "id.item")
    private Set<ItemPedidoEntity> itemPedidos = new HashSet<>();

    public ItemEntity() {
    }

    public ItemEntity(Categoria categoria, UUID id, Set<ItemPedidoEntity> itemPedidos, String nome, int preco) {
        this.categoria = categoria;
        this.id = id;
        this.itemPedidos = itemPedidos;
        this.nome = nome;
        this.preco = preco;
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

    public Set<ItemPedidoEntity> getItemPedidos() {
        return itemPedidos;
    }

    public List<PedidoEntity> getPedidos() {
        return itemPedidos.stream().map(x -> x.getPedido()).toList();
    }
}