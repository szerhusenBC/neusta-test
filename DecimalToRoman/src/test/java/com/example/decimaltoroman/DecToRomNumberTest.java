package com.example.decimaltoroman;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class DecToRomNumberTest {

    public DecToRomNumber converter = new DecToRomNumber();

    @Test
    void converterRomanToIntCD() {
        assertThat(DecToRomNumber.converter(400)).isEqualTo("CD");
    }

    @Test
    void converterRomanToIntV() {
        assertThat(DecToRomNumber.converter(5)).isEqualTo("V");
    }

    @Test
    void converterRomanToIntM() {
        assertThat(DecToRomNumber.converter(1000)).isEqualTo("M");
    }

    @Test
    void converterRomanToIntI() {
        assertThat(DecToRomNumber.converter(1)).isEqualTo("I");

    }

    @Test
    void converterRomanToIntIIII() {
        assertThat(DecToRomNumber.converter(4)).isEqualTo("IV");

    }

    @Test
    void converterRomanToIntDXLIX() {
        assertThat(DecToRomNumber.converter(549)).isEqualTo("DXLIX");


    }
}