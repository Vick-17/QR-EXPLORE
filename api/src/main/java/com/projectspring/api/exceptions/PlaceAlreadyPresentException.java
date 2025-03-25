package com.projectspring.api.exceptions;

public class PlaceAlreadyPresentException extends RuntimeException {
    public PlaceAlreadyPresentException(String message) {
        super(message);
    }

}
