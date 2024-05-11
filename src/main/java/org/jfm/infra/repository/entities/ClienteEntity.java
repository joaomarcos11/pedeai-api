package org.jfm.infra.repository.entities;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "clientes")
@NamedQueries({
        @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM ClienteEntity c"),
        @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM ClienteEntity c WHERE c.id = :id"),
        @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM ClienteEntity c WHERE c.cpf = :cpf"),
        @NamedQuery(name = "Cliente.update", query = "UPDATE ClienteEntity c SET c.nome = :nome, c.cpf = :cpf, c.email = :email, c.ativo = :ativo WHERE c.id = :id"),
        @NamedQuery(name = "Cliente.delete", query = "DELETE FROM ClienteEntity c WHERE c.id = :id"),
})
@Getter
@Setter
public class ClienteEntity {
    @Id
    private UUID id;
    private String nome, cpf, email;
    private boolean ativo;
};