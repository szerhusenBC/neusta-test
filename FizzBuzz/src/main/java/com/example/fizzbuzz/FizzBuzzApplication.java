package com.example.fizzbuzz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FizzBuzzApplication {

    public static void main(String[] args) {

        FizzBuzzResult fizzBuzzResult = new FizzBuzzResult();

        for (int i = 1; i <= 100; i++) {
            String number = fizzBuzzResult.fizzBuzz(i);

            System.out.println(number);
        }
    }
}



