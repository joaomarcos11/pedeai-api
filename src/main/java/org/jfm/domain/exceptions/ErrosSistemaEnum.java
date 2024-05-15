package org.jfm.domain.exceptions;

public enum ErrosSistemaEnum {
    CLIENTE_NOT_FOUND("Cliente não encontrado"),
    CLIENTE_CPF_EMAIL_CONFLICT("CPF/Email já cadastrados"),
    PARAM_INVALID("Parâmetro(s) invalido(s)");


    private final String message;

    ErrosSistemaEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
