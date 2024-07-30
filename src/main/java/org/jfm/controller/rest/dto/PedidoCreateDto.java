package org.jfm.controller.rest.dto;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record PedidoCreateDto(
        @Schema(name = "idCliente", example = "1", required = true) UUID idCliente,
        @Schema(name = "itensPedidos", example = "[{\"nome\": \"SANDUICHEICHE\", \"categoria\": \"0\", \"preco\": \"650\", \"quantidade\": 2}]", required = true) List<ItemPedidoCreateDto> itensPedidos) {
}
