package org.jfm.infra.repository.adaptersql.entities;

import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.jfm.domain.entities.enums.Categoria;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "itens")
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM ItemEntity i"),
        @NamedQuery(name = "Item.findById", query = "SELECT i FROM ItemEntity i WHERE i.id = :id"),
        @NamedQuery(name = "Item.update", query = "UPDATE ItemEntity i SET i.nome = :nome, i.preco = :preco, i.categoria = :categoria, i.descricao = :descricao, i.imagem = :imagem WHERE i.id = :id"),
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
    private String descricao;
    private String imagem;

    @OneToMany(mappedBy = "item")
    Set<ItemPedidoEntity> itensPedidos;

}
