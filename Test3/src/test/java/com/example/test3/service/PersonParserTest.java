package com.example.test3.service;

import com.example.test3.domain.Person;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @Test
    void personHatNurNachname() {
        assertThatThrownBy(() -> parser.parse("Supper"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");
    }
    @Test
    void personHatNurVorname(){
        assertThatThrownBy(()-> parser.parse( "Frank"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");
}
    @Test
    void personHatNurVornameUndNachname(){
        assertThatThrownBy(()-> parser.parse( "Frank Supper"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatZweiTitle(){
        assertThatThrownBy(()-> parser.parse( "Prof.Dr."))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");
    }
    @Test
    void personHatFalscheTitle(){
        assertThatThrownBy(()-> parser.parse("Professor"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");
        
    }
    @Test
    void personHatKeinLdpUser(){
        assertThatThrownBy(()-> parser.parse("Dr.Frank Supper" ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatFalscheTitleNachnameLdpUser(){
        assertThatThrownBy(()-> parser.parse("F.Supper(fsupper)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }


    // "Frank Supper ()"
    // "Frank (fsupper)"
    // "Dr. Dr. Frank Supper (fsupper)"
    // "Prof. Frank Supper (fsupper)"
    // "F. Supper (fsupper)"


}
