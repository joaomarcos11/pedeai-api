package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record ItemPedidoCreateDto(
                @Schema(name = "idItem", example = "1", required = true) UUID idItem,
                @Schema(name = "quantidade", example = "1", required = true) int quantidade) {
}
