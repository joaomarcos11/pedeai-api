package org.jfm.infra.payment.adaptermock;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.jfm.domain.entities.enums.IdentificacaoPagamento;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.PaymentException;
import org.jfm.domain.ports.PagamentoGateway;
import org.jfm.domain.valueobjects.Pagamento;
import org.jfm.infra.payment.adaptermock.restclient.PaymentAdapterMock;
import org.jfm.infra.payment.adaptermock.restclient.dto.RequestDto;
import org.jfm.infra.payment.adaptermock.restclient.dto.ResponseDto;

@ApplicationScoped
public class PagamentoGatewayImpl implements PagamentoGateway {

    @RestClient 
    PaymentAdapterMock restClient;

    @Override
    public Pagamento criarPagamento (
        int valor,
        String descricao,
        IdentificacaoPagamento tipoIdentificacao,
        String identificacao
    ) {

        try {
            UUID uuid = UUID.randomUUID();
    
            RequestDto request = new RequestDto(uuid, valor, descricao, identificacao);
            ResponseDto response = restClient.criarPagamento(request);
    
            return new Pagamento(uuid, response.id(), response.qrCodeBase64());

        } catch (Exception e) {
            throw new PaymentException(ErrosSistemaEnum.PAYMENT_ERROR.getMessage());
        }
    }

}
