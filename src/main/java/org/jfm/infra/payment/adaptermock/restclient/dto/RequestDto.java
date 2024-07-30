package org.jfm.infra.payment.adaptermock.restclient.dto;

import java.util.UUID;

public record RequestDto(UUID id, int valor, String descricao, String cpf) {
}
