package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record PedidoAddRemoveItemDto(
        @Schema(name = "id", example = "1", required = true) UUID id,
        @Schema(name = "idItem", example = "1", required = true) UUID idItem) {
}
