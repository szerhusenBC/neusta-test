package com.example.test2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldConroller {

    @GetMapping ("/hallo")
    public String helloWorld(){
        return "hello World";
    }
}
