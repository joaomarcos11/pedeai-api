package org.jfm.controller.rest.dto;

import java.time.Instant;

public record PagamentoGatewayWebhookDto(String action, DataPagamentoGatewayDto data, String apiVersion, Instant dateCreated, String id,
        boolean liveMode, String type, int userId) {
}
