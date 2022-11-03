package com.example.fizzbuzz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzErgebnisTest {
    public final FizzBuzzErgebnis ergebnis = new FizzBuzzErgebnis();

    @Test
    void fizzBuzz15() {
        String wert = ergebnis.fizzBuzz(15);
        assertEquals("FizzBuzz",wert);
    }
    @Test
    void fizzBuzz1(){
        String wert =ergebnis.fizzBuzz(1);
        assertEquals("1",wert);

    }

    @Test
    void fizzBuzz2() {
        String wert = ergebnis.fizzBuzz(2);
        assertEquals("2", wert);
    }
    @Test
    void fizzBuzz3() {
        String wert = ergebnis.fizzBuzz(3);
        assertEquals("Fizz", wert);
    }
    @Test
    void fizzBuzz4() {
        String wert = ergebnis.fizzBuzz(4);
        assertEquals("4", wert);
    }
    @Test
    void fizzBuzz5() {
        String wert = ergebnis.fizzBuzz(5);
        assertEquals("Buzz", wert);
    }
}