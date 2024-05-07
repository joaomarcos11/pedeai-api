package org.jfm.infra.repository.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ingredientes")
@NamedQueries({
        @NamedQuery(name = "Ingrediente.findAll", query = "SELECT i FROM IngredienteEntity i"),
        @NamedQuery(name = "Ingrediente.findById", query = "SELECT i FROM IngredienteEntity i WHERE i.id = :id"),
        @NamedQuery(name = "Ingrediente.update", query = "UPDATE IngredienteEntity i SET i.descricao = :descricao, i.preco = :preco, i.vegetariano = :vegetariano, i.vegano = :vegano, i.adicional = :adicional WHERE i.id = :id"),
        @NamedQuery(name = "Ingrediente.delte", query = "DELETE FROM IngredienteEntity i WHERE i.id = :id"),
})
@Getter
@Setter
public class IngredienteEntity {

    @Id
    private int id;
    private String descricao;
    private int preco;
    private boolean vegetariano, vegano, adicional;

}
