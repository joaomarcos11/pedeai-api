package org.jfm.infra.repository.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.jfm.domain.entities.enums.Categoria;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens")
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM ItemEntity i"),
        @NamedQuery(name = "Item.findById", query = "SELECT i FROM ItemEntity i WHERE i.id = :id"),
        @NamedQuery(name = "Item.update", query = "UPDATE ItemEntity i SET i.nome = :nome, i.preco = :preco, i.categoria = :categoria WHERE i.id = :id"),
        @NamedQuery(name = "Item.delete", query = "DELETE FROM ItemEntity i WHERE i.id = :id"),
        @NamedQuery(name = "Item.findByCategoria", query = "SELECT i FROM ItemEntity i WHERE i.categoria = :categoria"),
})
@Getter
@Setter
public class ItemEntity {
    @Id
    private UUID id;
    private String nome;
    private int preco;
    private Categoria categoria;

    @ManyToMany(cascade = { CascadeType.ALL }) // TODO: verificar esse tipo de cascade e outras variaveis
    @JoinTable(name = "itens_pedidos", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = {
            @JoinColumn(name = "pedido_id") })
    Set<PedidoEntity> pedidos = new HashSet<>();
}
