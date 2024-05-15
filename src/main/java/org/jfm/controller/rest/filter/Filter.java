package org.jfm.controller.rest.filter;

import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jfm.domain.exceptions.EntityNotFoundException;

import jakarta.ws.rs.core.Response;

public class Filter {
    
    @ServerExceptionMapper
    public RestResponse<String> getFilter (EntityNotFoundException t) {
        return RestResponse.status(Response.Status.NOT_FOUND, t.getMessage());
    }
    
}
