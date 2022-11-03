package com.example.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzApplicationTest {
    public FizzBuzzErgebnis fizzBuzzErgebnis = new FizzBuzzErgebnis();


    @Test
    public void testFizzBuzz15() {
        String wert = fizzBuzzErgebnis.fizzBuzz(15);
        assertEquals("FizzBuzz", wert);

    }

    @Test
    public void testFlizzBuzz1() {
        String wert = fizzBuzzErgebnis.fizzBuzz(1);
        assertEquals("1", wert);

    }

    @Test
    public void testFizzBuzz2() {
        String wert = fizzBuzzErgebnis.fizzBuzz(2);
        assertEquals("2", wert);


    }

    @Test
    public void testFizzBuzz3() {
        String wert = fizzBuzzErgebnis.fizzBuzz(3);
        assertEquals("Fizz", wert);

    }

    @Test
    public void testFizzBuzz5() {
        String wert = fizzBuzzErgebnis.fizzBuzz(5);
        assertEquals("Buzz", wert);

    }

    @Test
    public void tesFizzBuzz30() {
        String wert = fizzBuzzErgebnis.fizzBuzz(30);
        assertEquals("FizzBuzz", wert);


    }
}