package org.jfm.infra.payment.adaptermercadopago;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// SDK de Mercado Pago
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.resources.payment.Payment;

import jakarta.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jfm.domain.entities.enums.IdentificacaoPagamento;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.PaymentException;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.valueobjects.PagamentoPix;

@ApplicationScoped
public class PagamentoGatewayImpl implements PagamentoGateway {

    @ConfigProperty(name = "mercadopago.access.token") 
    String ACCESS_TOKEN;

    @ConfigProperty(name = "webhook.url")
    String WEBHOOK_URL;

    @Override
    public PagamentoPix criarPagamento2(
        int valor,
        String descricao,
        IdentificacaoPagamento tipoIdentificacao,
        String identificacao
    ) {
        try {
            // TODO: https://www.mercadopago.com.br/developers/pt/docs/checkout-api/integration-configuration/integrate-with-pix#editor_9

            UUID uuid = UUID.randomUUID();
    
            // TODO: melhorar abaixo
            String valorString = Integer.toString(valor);
            String valorConvertido = valorString.substring(0, valorString.length()-2) + "."+ valorString.substring(valorString.length()-2, valorString.length());
    
            // 31 minutos para pagamento
            OffsetDateTime dataExpiracaoPagamento = OffsetDateTime.now().plusMinutes(31);
    
            // TODO: configurar env var
            MercadoPagoConfig.setAccessToken(ACCESS_TOKEN);
    
            Map<String, String> customHeaders = new HashMap<>();
                customHeaders.put("x-idempotency-key", uuid.toString());
            
            MPRequestOptions requestOptions = MPRequestOptions.builder()
                .customHeaders(customHeaders)
                .build();
    
            PaymentClient client = new PaymentClient();
    
            PaymentCreateRequest paymentCreateRequest =
            PaymentCreateRequest.builder()
                .transactionAmount(new BigDecimal(valorConvertido))
                .description(descricao)
                .paymentMethodId("pix")
                .dateOfExpiration(dataExpiracaoPagamento)
                .notificationUrl(WEBHOOK_URL)
                .payer(
                    PaymentPayerRequest.builder()
                        .email("d234a622-7cb6-4583-b2fe-c0213b0988e2@emailhook.site")
                        .firstName("Teste")
                        .identification(
                            IdentificationRequest.builder().type("CPF").number("19119119100").build())
                        .build())
                .build();
    
            Payment payment = client.create(paymentCreateRequest, requestOptions);
    
            Long paymentId = payment.getId();
            String qrCode = payment.getPointOfInteraction().getTransactionData().getQrCodeBase64();
    
            return new PagamentoPix(uuid, paymentId, qrCode);
            
        } catch (Exception e) {
            // TODO: ver toda a questão de tratamento de erros (incluir mensagem original)
            throw new PaymentException(ErrosSistemaEnum.PAYMENT_ERROR.getMessage());
        }
    }

    @Override
    public byte[] criarPagamento(int valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarPagamento'");
    }

    @Override
    public boolean pagar(byte[] informacao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pagar'");
    }

}
