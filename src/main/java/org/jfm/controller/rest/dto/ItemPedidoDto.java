package org.jfm.controller.rest.dto;

import org.jfm.domain.entities.enums.Categoria;

public record ItemPedidoDto(String nome, Categoria categoria, int preco, int quantidade) {
}
