package com.example.fizzbuzz;

public class FizzBuzzErgebnis {
    public String fizzBuzz(int zahl) {
        if (zahl % 3 == 0 && zahl % 5 == 0) {
            return "FizzBuzz";
        } else if (zahl % 5 == 0) {
            return "Buzz";
        } else if (zahl % 3 == 0) {
            return "Fizz";


        }
        return "" + zahl;
    }
}