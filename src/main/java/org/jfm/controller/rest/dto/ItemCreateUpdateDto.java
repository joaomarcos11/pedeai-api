package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Categoria;

public record ItemCreateUpdateDto(
                @Schema(name = "nome", example = "1", required = true) String nome,
                @Schema(name = "preco", example = "1", required = true) int preco,
                @Schema(name = "categoria", example = "1", required = true) Categoria categoria,
                @Schema(name = "descricao", example = "1", required = true) String descricao,
                @Schema(name = "imagem", example = "1", required = true) String imagem) {
}
