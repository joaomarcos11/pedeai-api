package org.jfm.infra.payment.adaptermock.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jfm.infra.payment.adaptermock.restclient.dto.RequestDto;
import org.jfm.infra.payment.adaptermock.restclient.dto.ResponseDto;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/pagamento")
@RegisterRestClient(configKey = "payment-adapter-mock")
public interface PaymentAdapterMock {
    
    @POST
    ResponseDto criarPagamento(RequestDto request);

}


