package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public record ClienteDto(
                @Schema(name = "nome", example = "Gabriel Medina", required = true) String nome,
                @Schema(name = "cpf", example = "98798798798", required = true) String cpf,
                @Schema(name = "email", example = "gabrielmedina@surf.com", required = true) String email,
                @Schema(name = "ativo", example = "true", required = true) boolean ativo) {
}