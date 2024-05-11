package org.jfm.controller.rest.dto;

public record ClienteCreateUpdateDTO(
        String nome,
        String cpf,
        String email,
        boolean ativo) {
}