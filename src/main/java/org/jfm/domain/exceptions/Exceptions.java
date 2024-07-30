package org.jfm.domain.exceptions;

public class Exceptions {

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException (String message) {
            super(message);
        }
    }

    public static class InvalidEntityException extends RuntimeException {
        public InvalidEntityException (String message) {
            super(message);
            // TODO: adicionar mais campos onde qual validação está dando erro.
        }
    }

    public static class PagamentoException extends RuntimeException {
        public PagamentoException (String message) {
            super(message);
        }
    }

}
