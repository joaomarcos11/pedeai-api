package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Status;

public record PedidoUpdateDto(
        @Schema(name = "status", example = "1", required = true) Status status) {
}
