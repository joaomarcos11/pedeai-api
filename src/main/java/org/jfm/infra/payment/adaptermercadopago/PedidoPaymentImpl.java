package org.jfm.infra.payment.adaptermercadopago;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// SDK de Mercado Pago
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodsRequest;
import com.mercadopago.client.preference.PreferencePaymentTypeRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.common.Address;
import com.mercadopago.resources.common.Phone;
import com.mercadopago.resources.preference.PreferenceBackUrls;

import org.jfm.domain.ports.PedidoPayment;

public class PedidoPaymentImpl implements PedidoPayment {

    public static void pagar() {
        MercadoPagoConfig.setAccessToken("PROD_ACCESS_TOKEN");
    
        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .id("item-ID-1234")
                .title("Meu produto")
                .currencyId("BRL")
                .pictureUrl("https://www.mercadopago.com/org-img/MP3/home/logomp3.gif")
                .description("Descrição do Item")
                .categoryId("art")
                .quantity(1)
                .unitPrice(new BigDecimal("75.76"))
                .build();
    
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
    
        PreferencePayerRequest payer = PreferencePayerRequest.builder()
            .name("NOME DO CARA")
            .surname("SOBRENOME DO CARA")
            .email("EMAIL DO CARA")
            .phone(
                PhoneRequest.builder()
                    .areaCode("61")
                    .number("55555-4444")
                    .build()
                )
            .identification(
                IdentificationRequest.builder()
                    .type("CPF")
                    .number("CPF DO CARA")
                    .build()
                )
            .address(AddressRequest.builder()
                    .streetName("RUA DO CARA")
                    .streetNumber("666")
                    .zipCode("666666333")
                    .build()
                )
            .build(); 

    
        PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
            .success("http://localhost:3030/v1/api/pagamentos/sucesso")
            .failure("http://localhost:3030/v1/api/pagamentos/falha")
            .pending("http://localhost:3030/v1/api/pagamentos/pendente")
            .build();

        List<PreferencePaymentMethodRequest> excludedPaymentMethods = new ArrayList<>();
        excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("bolbradesco").build());
        excludedPaymentMethods.add(PreferencePaymentMethodRequest.builder().id("pec").build());
    
        List<PreferencePaymentTypeRequest> excludedPaymentTypes = new ArrayList<>();
        excludedPaymentTypes.add(PreferencePaymentTypeRequest.builder().id("debit_card").build());

        PreferencePaymentMethodsRequest paymentMethods = PreferencePaymentMethodsRequest.builder()
            .excludedPaymentMethods(excludedPaymentMethods)
            .excludedPaymentTypes(excludedPaymentTypes)
            .build();
    
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                .items(items)
                .payer(payer)
                .backUrls(backUrls)
                .autoReturn("approved")
                .paymentMethods(paymentMethods)
                .notificationUrl("https://www.your-site.com/ipn")
                .statementDescriptor("MEUNEGOCIO")
                .externalReference("Reference_1234")
                .expires(true)
                .expirationDateFrom("2016-02-01T12:00:00.000-04:00")
                .expirationDateTo("2016-02-28T12:00:00.000-04:00")
                .build();
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
