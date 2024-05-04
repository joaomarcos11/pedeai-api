package org.jfm.controller.rest;

import java.util.List;

import org.jfm.domain.entities.Cliente;
import org.jfm.domain.services.ClienteService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cliente")
public class ClienteResource {

    @Inject
    ClienteService clienteService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criar(Cliente cliente) {
        int clienteId = clienteService.criar(cliente);
        return Response.status(Response.Status.CREATED).entity(clienteId).build();
    };

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscar(@QueryParam("cpf") String cpf) {
        if (cpf == null) {
            List<Cliente> clientes = clienteService.listar();
            return Response.status(Response.Status.OK).entity(clientes).build();
        }

        Cliente cliente = clienteService.buscarPorCpf(cpf);
        return Response.status(Response.Status.OK).entity(cliente).build();
    };

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") int id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return Response.status(Response.Status.OK).entity(cliente).build();
    };

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(Cliente cliente) {
        clienteService.editar(cliente);
        return Response.status(Response.Status.OK).build();
    };

    @DELETE
    @Path("/{id}")
    public Response remover(@PathParam("id") int id) {
        clienteService.remover(id);
        return Response.status(Response.Status.OK).build();
    };

}