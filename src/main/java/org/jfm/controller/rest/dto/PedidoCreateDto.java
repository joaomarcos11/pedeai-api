package org.jfm.controller.rest.dto;

import java.util.List;
import java.util.UUID;

public record PedidoCreateDto(UUID idCliente, List<ItemPedidoCreateDto> itensPedidos) {
}
