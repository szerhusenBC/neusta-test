package com.example.romantodecimal;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.example.romantodecimal.RomanToInt.converter;


@SpringBootApplication
public class RomanToDecimal {


    public static void main(String[] args) {

        converter("DCCLXXVII");

    }

}




