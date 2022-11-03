package com.example.fizzbuzz;

public class FizzBuzzResult {
    public String fizzBuzz(int number) {

        if (number % 3 == 0 && number % 5 == 0) {
            return "FizzBuzz";

        } else if (number % 5 == 0) {
            return "Buzz";

        } else if (number % 3 == 0) {
            return "Fizz";

        }
            return "" + number;
    }
}