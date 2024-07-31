package org.jfm.domain.valueobjects;

import java.util.UUID;

public record Pagamento(UUID id, Long paymentId, String qrCode) {
}
