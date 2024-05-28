package org.jfm.controller.rest.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {
    private UUID id;
    private UUID idCliente;
    private Status status;
    private Instant dataCriacao;
    private List<ItemPedidoDto> itens;
}
