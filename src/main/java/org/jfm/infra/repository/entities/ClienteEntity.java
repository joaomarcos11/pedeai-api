package org.jfm.infra.repository.entities;

import java.time.Instant;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "Cliente.create", query = "INSERT INTO ClienteEntity c (c.id, c.nome, c.cpf, c.email, c.dataCriacao, c.dataAtualizacao) VALUES (:id, :nome, :cpf, :email, :dataCriacao, :dataAtualizacao)"),
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM ClienteEntity c"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM ClienteEntity c WHERE c.id = :id"),
    @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM ClienteEntity c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Cliente.update", query = "UPDATE ClienteEntity c SET c.nome = :nome, c.cpf = :cpf, c.email = :email, c.dataAtualizacao = :dataAtualizacao WHERE c.id = :id"),
    @NamedQuery(name = "Cliente.delete", query = "DELETE FROM ClienteEntity c WHERE c.id = :id"),
})
public class ClienteEntity {

    @Id
    private int id;
    private String nome, cpf, email;
    private Instant dataCriacao, dataAtualizacao;
    private boolean ativo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Instant getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }
    public void setDataAtualizacao(Instant dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
    public boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
};