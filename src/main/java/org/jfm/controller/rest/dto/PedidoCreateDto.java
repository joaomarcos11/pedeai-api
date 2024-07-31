package org.jfm.controller.rest.dto;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record PedidoCreateDto(
                @Schema(name = "idCliente", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54", required = true) UUID idCliente,
                @Schema(name = "itensPedidos", example = "[{\"id\": \"257ae14b-8bb7-4a80-9a68-22197f72ff47\", \"quantidade\": 2}]", required = true) List<ItemPedidoCreateDto> itensPedidos) {
}
