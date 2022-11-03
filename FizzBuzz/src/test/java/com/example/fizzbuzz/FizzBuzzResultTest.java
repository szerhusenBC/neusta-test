package com.example.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzResultTest {
    public final FizzBuzzResult result = new FizzBuzzResult();

    @Test
    void fizzBuzz15() {
        String value = result.fizzBuzz(15);
        assertEquals("FizzBuzz", value);

    }

    @Test
    void fizzBuzz1() {
        String value = result.fizzBuzz(1);
        assertEquals("1", value);

    }

    @Test
    void fizzBuzz2() {
        String value = result.fizzBuzz(2);
        assertEquals("2", value);

    }

    @Test
    void fizzBuzz3() {
        String value = result.fizzBuzz(3);
        assertEquals("Fizz", value);

    }

    @Test
    void fizzBuzz4() {
        String value = result.fizzBuzz(4);
        assertEquals("4", value);

    }

    @Test
    void fizzBuzz5() {
        String value = result.fizzBuzz(5);
        assertEquals("Buzz", value);

    }
}