package com.example.romantodecimal;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class RomaToIntTest {
    public RomaToInt converter = new RomaToInt();

    @Test
    void converterRomanToIntCD() {
        String value = "CD";
        int result = 400;
        RomaToInt.converter("CD");
        assertThat(result).isEqualTo(400);

    }

    @Test
    void converterRomanToIntV() {
        String value = "V";
        int result = 5;
        RomaToInt.converter("V");
        assertThat(result).isEqualTo(5);

    }

    @Test
    void converterRomanToIntM() {
        String value = "M";
        int result = 1000;
        RomaToInt.converter("M");
        assertThat(result).isEqualTo(1000);

    }

    @Test
    void converterRomanToIntI() {
        String value = "I";
        int result = 1;
        RomaToInt.converter("I");
        assertThat(result).isEqualTo(1);

    }

    @Test
    void converterRomanToIntIIII() {
        String value = "IV";
        int result = 4;
        RomaToInt.converter("IV");
        assertThat(result).isEqualTo(4);

    }

}