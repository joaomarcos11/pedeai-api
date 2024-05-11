package org.jfm.controller.rest.dto;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Status;

public record PedidoDTO(
    UUID id,
    UUID idCliente,
    Status status,
    List<Item> itens) {
}
