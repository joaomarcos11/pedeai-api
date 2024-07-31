package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Categoria;

public record ItemPedidoDto(
        @Schema(name = "nome", example = "1", required = true) String nome,
        @Schema(name = "categoria", example = "1", required = true) Categoria categoria,
        @Schema(name = "preco", example = "1", required = true) int preco,
        @Schema(name = "quantidade", example = "1", required = true) int quantidade) {
}
