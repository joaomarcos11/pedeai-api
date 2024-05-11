package org.jfm.controller.rest.dto;

import java.util.UUID;

public record PedidoAddRemoveItemDto(
    UUID id,
    UUID idItem) {
    
}
