package org.jfm.controller.rest.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Status;

public record PedidoDto(
        UUID id,
        UUID idCliente,
        @Schema(name = "Status", example = "1", required = true) Status status,
        @Schema(name = "Data de criação", example = "2024-05-27 20:48:15.064807+00", required = true) Instant dataCriacao,
        @Schema(name = "Itens", example = "[{\"nome\": \"SANDUICHEICHE\", \"categoria\": \"0\", \"preco\": \"650\", \"quantidade\": 2}]", required = true) List<ItemPedidoDto> itens) {
}
