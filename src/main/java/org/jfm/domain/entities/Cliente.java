package org.jfm.domain.entities;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import jakarta.persistence.Entity;
import java.time.Instant;

@Entity
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByCpf", query = "SELECT c FROM Cliente c WHERE c.cpf = :cpf"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findAtivos", query = "SELECT c FROM Cliente c WHERE c.ativo = true"),
    @NamedQuery(name = "Cliente.findInativos", query = "SELECT c FROM Cliente c WHERE c.ativo = false")
})
public class Cliente {
  private int id;
  private String nome;
  private String cpf;
  private String email;
  private Instant dataCriacao;
  private Instant dataAtualizacao;
  private boolean ativo;

  public Cliente() {
    super();
  }

  public Cliente(int id, String nome, String cpf, String email, Instant dataCriacao, Instant dataAtualizacao,
      boolean ativo) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.dataCriacao = dataCriacao;
    this.dataAtualizacao = dataAtualizacao;
    this.ativo = ativo;
  }

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
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
    Cliente other = (Cliente) obj;
    if (id != other.id)
      return false;
    return true;
  }

}