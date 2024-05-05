package org.jfm.controller.rest.dto;

public record ClienteCreateUpdate(String nome, String cpf, String email, boolean ativo) {
}
