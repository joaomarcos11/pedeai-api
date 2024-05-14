package org.jfm.domain.entities;

import java.util.UUID;

public class Cliente {
  private UUID id;
  private String nome;
  private String cpf;
  private String email;
  private boolean ativo;

  public Cliente() {
  }; // TODO: constructor vazio?

  public Cliente(UUID id, String nome, String cpf, String email, boolean ativo) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.ativo = ativo;
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
    Cliente other = (Cliente) obj;
    if (!this.id.equals(other.id)) // TODO: ver se não fiz caca aqui
      return false;
    return true;
  }

}