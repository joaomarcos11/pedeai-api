package org.jfm.domain.valueobjects;

import java.util.UUID;

public record PagamentoPix(UUID id, Long paymentId, String qrCode) {
}
