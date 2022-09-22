package br.com.RestWithMockito.services.exceptions;

public class ObjectNotFoundExceptions extends RuntimeException{

    public ObjectNotFoundExceptions(String message) {
        super(message);
    }
}
