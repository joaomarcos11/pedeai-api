package org.jfm.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

public class ApiException extends WebApplicationException {
    private static final long serialVersionUID = 1L;

    public ApiException(String message) {
        super(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                     .entity(new ErrorDetails(message))
                     .build());
    }

    public ApiException(String message, Response.Status status) {
        super(Response.status(status)
                     .entity(new ErrorDetails(message))
                     .build());
    }

    public ApiException(String message, Response.Status status, Throwable cause) {
        super(message, cause, status);
    }

}
