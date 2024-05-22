package org.jfm.controller.rest.dto;

import java.util.Map;
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
    private Map<UUID, Integer> itens;
}
