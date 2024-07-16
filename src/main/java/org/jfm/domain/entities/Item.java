package org.jfm.domain.entities;

import java.util.UUID;

import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.exceptions.Exceptions.InvalidEntityException;

public class Item {
    private UUID id;
    private String nome;
    private int preco;
    private Categoria categoria;
    private String descricao;
    private String imagem;

    public Item() {
        super();
    }

    public Item(UUID id, String nome, int preco, Categoria categoria, String descricao, String imagem) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.imagem = imagem;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public void validar() {
        if (this.nome == null || this.nome.isBlank()) {
           throw new InvalidEntityException("nome não pode ser vazio"); 
        }

        if (this.preco < 0 ) {
           throw new InvalidEntityException("preço não pode ser negativo"); 
        }
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

}
