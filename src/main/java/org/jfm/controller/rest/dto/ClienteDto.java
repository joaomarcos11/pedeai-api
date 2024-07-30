package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Data;

@Data
public class ClienteDto {
    @Schema(name = "Nome", example = "Gabriel Medina", required = true)
    private String nome;
    @Schema(name = "CPF", example = "987.987.987-98", required = true)
    private String cpf;
    @Schema(name = "Email", example = "gabrielmedina@surf.com", required = true)
    private String email;
    @Schema(name = "Ativo", example = "true", required = true)
    private boolean ativo;
}