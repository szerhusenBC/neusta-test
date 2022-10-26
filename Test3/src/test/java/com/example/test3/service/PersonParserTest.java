package com.example.test3.service;

import com.example.test3.domain.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonParserTest {

    private final PersonParser parser = new PersonParser();

    @Test
    void vollePersonInfo() {
        Person person = parser.parse("Dr. Frank von Supper (fsupper)");

        assertThat(person.getFirstName()).isEqualTo("Frank");
        assertThat(person.getLastName()).isEqualTo("Supper");
        assertThat(person.getLdapUser()).isEqualTo("fsupper");
        assertThat(person.getTitle()).isEqualTo("Dr.");
        assertThat(person.getNameAddition()).isEqualTo("von");
    }

    @Test
    void personOhneTitel() {
        Person person = parser.parse("Frank von Supper (fsupper)");

        assertThat(person.getFirstName()).isEqualTo("Frank");
        assertThat(person.getLastName()).isEqualTo("Supper");
        assertThat(person.getLdapUser()).isEqualTo("fsupper");
        assertThat(person.getNameAddition()).isEqualTo("von");
        assertThat(person.getTitle()).isEqualTo("");
    }

    @Test
    void personOhneTitelUndOhneNameAddition() {
        Person person = parser.parse("Frank Supper (fsupper)");

        assertThat(person.getFirstName()).isEqualTo("Frank");
        assertThat(person.getLastName()).isEqualTo("Supper");
        assertThat(person.getLdapUser()).isEqualTo("fsupper");
        assertThat(person.getNameAddition()).isEqualTo("");
        assertThat(person.getTitle()).isEqualTo("");
    }
}