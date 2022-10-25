package com.example.test3.service;

public class PersonAlreadyExistsException extends RuntimeException {

    private ErrorDto errorDto;

    public PersonAlreadyExistsException(ErrorDto errorDto) {
        super();
        this.errorDto = errorDto;
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }
}
