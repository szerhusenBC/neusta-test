package com.example.test3.service;

public class ErrorDto {
    private int code;
    private String message;


    public ErrorDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {

        return  (code + ": " + message) ;

    }
}
