package org.jfm.domain.exceptions;

public enum ErrosSistemaEnum {
    DATABASE_ERROR("Erro de persistência"),
    CLIENTE_NOT_FOUND("Cliente não encontrado"),
    CLIENTE_CPF_EMAIL_CONFLICT("CPF/Email já cadastrados"),
    PARAM_INVALID("Parâmetro(s) invalido(s)"),
    ITEM_NOT_FOUND("Item não encontrado"),
    PEDIDO_NOT_FOUND("Pedido não encontrado");


    private final String message;

    ErrosSistemaEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
