package com.example.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzApplicationTest {
    public FizzBuzzResult fizzBuzzResult = new FizzBuzzResult();


    @Test
    public void testFizzBuzz15() {
        String value = fizzBuzzResult.fizzBuzz(15);
        assertEquals("FizzBuzz", value);

    }

    @Test
    public void testFlizzBuzz1() {
        String value = fizzBuzzResult.fizzBuzz(1);
        assertEquals("1", value);

    }

    @Test
    public void testFizzBuzz2() {
        String value = fizzBuzzResult.fizzBuzz(2);
        assertEquals("2", value);

    }

    @Test
    public void testFizzBuzz3() {
        String value = fizzBuzzResult.fizzBuzz(3);
        assertEquals("Fizz", value);

    }

    @Test
    public void testFizzBuzz5() {
        String value = fizzBuzzResult.fizzBuzz(5);
        assertEquals("Buzz", value);

    }

    @Test
    public void testFizzBuzz30() {
        String value = fizzBuzzResult.fizzBuzz(30);
        assertEquals("FizzBuzz", value);

    }

    @Test
    public void testFizzBuzz100() {
        String value = fizzBuzzResult.fizzBuzz(100);
        assertEquals("Buzz", value);

    }
}