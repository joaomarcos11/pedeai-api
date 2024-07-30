package org.jfm.controller.rest.dto;

import java.time.Instant;
import java.util.UUID;

public record PagamentoGatewayWebhookDto(String action, DataPagamentoGatewayDto data, String apiVersion, Instant dateCreated, UUID id,
        boolean liveMode, String type, int userId) {
}
