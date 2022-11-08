package com.example.decimaltoroman;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.decimaltoroman.DecToRomNumber.converter;


@SpringBootApplication
public class DecimalToRomanApplication {


    public static void main(String[] args) {

        System.out.println(converter(99));

    }
}
