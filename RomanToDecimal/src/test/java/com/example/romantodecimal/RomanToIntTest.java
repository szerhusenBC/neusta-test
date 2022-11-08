package com.example.romantodecimal;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class RomanToIntTest {
    public RomanToInt converter = new RomanToInt();

    @Test
    void converterRomanToIntCD() {
        String value = "CD";
        int result = 400;
        RomanToInt.converter("CD");
        assertThat(result).isEqualTo(400);

    }

    @Test
    void converterRomanToIntV() {
        String value = "V";
        int result = 5;
        RomanToInt.converter("V");
        assertThat(result).isEqualTo(5);

    }

    @Test
    void converterRomanToIntM() {
        String value = "M";
        int result = 1000;
        RomanToInt.converter("M");
        assertThat(result).isEqualTo(1000);

    }

    @Test
    void converterRomanToIntI() {
        String value = "I";
        int result = 1;
        RomanToInt.converter("I");
        assertThat(result).isEqualTo(1);

    }

    @Test
    void converterRomanToIntIIII() {
        String value = "IV";
        int result = 4;
        RomanToInt.converter("IV");
        assertThat(result).isEqualTo(4);

    }

}