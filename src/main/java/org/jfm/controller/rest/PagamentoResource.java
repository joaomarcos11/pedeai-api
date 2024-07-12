package org.jfm.controller.rest;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pagamento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Pagamento", description = "Operações relacionadas ao pagamento")
public class PagamentoResource {
    
    @POST
    @Path("/sucesso")
    public Response sucesso() {
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/falha")
    public Response falha() {
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/pendente")
    public Response pendente() {
        return Response.status(Response.Status.CREATED).build();
    }

}
