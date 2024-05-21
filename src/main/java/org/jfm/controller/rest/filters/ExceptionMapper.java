package org.jfm.controller.rest.filters;

import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jfm.domain.exceptions.EntityConflictException;
import org.jfm.domain.exceptions.EntityException;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorDetails;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.domain.exceptions.Exceptions.InvalidEntityException;
import org.jfm.domain.exceptions.ParamException;

import jakarta.ws.rs.core.Response;

public class ExceptionMapper {
    
    @ServerExceptionMapper
    public Response mapException(EntityNotFoundException t) {
        return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(EntityException t) {
        return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(EntityConflictException t) {
        return Response.status(Response.Status.CONFLICT)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(ParamException t) {
        return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(ErrorSqlException t) {
        return Response.status(Response.Status.BAD_GATEWAY)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

    @ServerExceptionMapper
    public Response mapException(InvalidEntityException t) {
        return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorDetails(t.getMessage()))
                        .build();
    }

}
