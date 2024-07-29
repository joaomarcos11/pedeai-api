package org.jfm.infra.payment.adaptermercadopago;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

// SDK de Mercado Pago
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.payment.PaymentAdditionalInfoPayerRequest;
import com.mercadopago.client.payment.PaymentAdditionalInfoRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentItemRequest;
import com.mercadopago.client.payment.PaymentOrderRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.client.payment.PaymentReceiverAddressRequest;
import com.mercadopago.client.payment.PaymentShipmentsRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.resources.payment.Payment;

import org.jfm.domain.entities.enums.IdentificacaoPagamento;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.PaymentException;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.valueobjects.Pagamento;

@ApplicationScoped
public class PagamentoGatewayImpl implements PagamentoGateway {

    @ConfigProperty(name = "mercadopago.access.token") 
    String ACCESS_TOKEN;

    @ConfigProperty(name = "webhook.url")
    String WEBHOOK_URL;

    @Override
    public Pagamento criarPagamento(int valor, String descricao, IdentificacaoPagamento tipoIdentificacao, String identificacao) {
        Payment payment = new Payment();

        try {
            // TODO: https://www.mercadopago.com.br/developers/pt/docs/checkout-api/integration-configuration/integrate-with-pix#editor_9

            UUID uuid = UUID.randomUUID();
            MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);

            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", uuid.toString());

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();

            PaymentClient client = new PaymentClient();

            List<PaymentItemRequest> items = new ArrayList<>();

            PaymentItemRequest item =
                PaymentItemRequest.builder()
                    .id("MLB2907679857")
                    .title("Point Mini")
                    .description("Point product for card payments via Bluetooth.")
                    .pictureUrl(
                        "https://http2.mlstatic.com/resources/frontend/statics/growth-sellers-landings/device-mlb-point-i_medium2x.png")
                    .categoryId("electronics")
                    .quantity(1)
                    .unitPrice(new BigDecimal("58.8"))
                    .build();

            items.add(item);

            PaymentCreateRequest createRequest =
                PaymentCreateRequest.builder()
                    .additionalInfo(
                        PaymentAdditionalInfoRequest.builder()
                            .items(items)
                            .payer(
                                PaymentAdditionalInfoPayerRequest.builder()
                                    .firstName("Test")
                                    .lastName("Test")
                                    .phone(
                                PhoneRequest.builder().areaCode("11").number("987654321").build())
                                    .build())
                            .shipments(
                                PaymentShipmentsRequest.builder()
                                    .receiverAddress(
                                        PaymentReceiverAddressRequest.builder()
                                            .zipCode("12312-123")
                                            .stateName("Rio de Janeiro")
                                            .cityName("Buzios")
                                            .streetName("Av das Nacoes Unidas")
                                            .streetNumber("3003")
                                            .build())
                                    .build())
                            .build())
                    .binaryMode(false)
                    .capture(false)
                    .description("Payment for product")
                    .externalReference("MP0001")
                    .installments(1)
                    .order(PaymentOrderRequest.builder().type("mercadolibre").build())
                    .payer(PaymentPayerRequest.builder()
                        .entityType("individual")
                        .type("customer")
                        .email("test_user_123@testuser.com")
                        .identification(IdentificationRequest.builder()
                            .type("CPF")
                            .number("01234567890")
                            .build())
                        .build())
                    .paymentMethodId("master")
                    .token("ff8080814c11e237014c1ff593b57b4d")
                    .transactionAmount(new BigDecimal("58.8"))
                    .build();
            payment = client.create(createRequest, requestOptions);
    
            Long paymentId = payment.getId();
            String qrCode = payment.getPointOfInteraction().getTransactionData().getQrCodeBase64();
    
            return new Pagamento(uuid, paymentId, qrCode);
            
        } catch (Exception e) {
            throw new PaymentException(ErrosSistemaEnum.PAYMENT_ERROR.getMessage());
        }
    }

}
