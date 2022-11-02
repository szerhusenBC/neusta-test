package com.example.test3.MockAbc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KlammerTest {

    private final ErsteKlammerFabrik ersteKlammerFabrik = mock(ErsteKlammerFabrik.class);
    private final ZweiteKlammerFabrik zweiteKlammerFabrik = mock (ZweiteKlammerFabrik.class);
    private final KlammerFabrik klammerFabrik = new KlammerFabrik(ersteKlammerFabrik, zweiteKlammerFabrik);

    @Test
    void klammerTest() {
        String input = "abc";
        String erwartetesErgebnis = "(abc)";

        when(ersteKlammerFabrik.ersteKlammer("abc")).thenReturn("(abc");
        when(zweiteKlammerFabrik.zweiteKlammer("(abc")).thenReturn("(abc)");



        String echtesErgebnis = klammerFabrik.klammer(input);


        assertThat(echtesErgebnis).isEqualTo(erwartetesErgebnis);
    }


}