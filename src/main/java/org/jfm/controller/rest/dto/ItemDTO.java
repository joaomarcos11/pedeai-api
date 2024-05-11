package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.jfm.domain.entities.enums.Categoria;

public record ItemDTO(
        UUID id,
        String nome,
        int preco,
        Categoria idCategoria) {
}
