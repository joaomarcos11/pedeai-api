package org.jfm.infra.repository;

import java.time.Instant;

public class ClienteDTO {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private Instant dataCriacao;
    private Instant dataAtualizacao;
    private boolean ativo;

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(int id, String nome, String cpf, String email, Instant dataCriacao, Instant dataAtualizacao,
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

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Instant getDataAtualizacao() {
        return dataAtualizacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

};