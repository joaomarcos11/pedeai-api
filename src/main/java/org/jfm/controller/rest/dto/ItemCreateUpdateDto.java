package org.jfm.controller.rest.dto;

import org.jfm.domain.entities.enums.Categoria;

public record ItemCreateUpdateDto(String nome, int preco, Categoria categoria, String descricao, String imagem) {
}
