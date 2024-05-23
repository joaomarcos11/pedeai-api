package org.jfm.domain.exceptions;

public class EntityInvalidException extends RuntimeException {
    public EntityInvalidException(String message) {
        super(message);
    }
}
