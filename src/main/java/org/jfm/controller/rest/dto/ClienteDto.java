package org.jfm.controller.rest.dto;

public record ClienteDto (String nome, String cpf, String email, boolean ativo) {
}