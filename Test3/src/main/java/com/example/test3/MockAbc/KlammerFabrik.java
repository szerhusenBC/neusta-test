package com.example.test3.MockAbc;

public class KlammerFabrik {

    private final ErsteKlammerFabrik ersteKlammerFabrik;
    private final ZweiteKlammerFabrik zweiteKlammerFabrik;

    public KlammerFabrik(ErsteKlammerFabrik ersteKlammerFabrik, ZweiteKlammerFabrik zweiteKlammerFabrik) {
        this.ersteKlammerFabrik = ersteKlammerFabrik;
        this.zweiteKlammerFabrik = zweiteKlammerFabrik;
    }

    public String klammer(String input) {
        return zweiteKlammerFabrik.zweiteKlammer(ersteKlammerFabrik.ersteKlammer(input));
    }
}
