package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record ItemPedidoCreateDto(
                @Schema(name = "idItem", example = "257ae14b-8bb7-4a80-9a68-22197f72ff47", required = true) UUID idItem,
                @Schema(name = "quantidade", example = "1", required = true) int quantidade) {
}
