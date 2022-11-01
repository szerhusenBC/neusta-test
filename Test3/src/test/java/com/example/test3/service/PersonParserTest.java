package com.example.test3.service;
import com.example.test3.domain.Person;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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
    void personOhneDaten() {
        Person person = parser.parse("");

        assertThat(person.getFirstName()).isEqualTo("");
        assertThat(person.getLastName()).isEqualTo("");
        assertThat(person.getLdapUser()).isEqualTo("");
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
    void personHatNurVornameUndNachname(){
        assertThatThrownBy(()-> parser.parse( "Frank Supper"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }

// [ggf. Titel] Vorname [ggf. Zweitname(n)] [ggf. Namenszusatz] Nachname (LDAP-Username)


    /***
     * 3: [ldap]
     *      Dr. Vorname Nachname
     *      Vorname von Nachname
     *      //-------------------
     *    [nachname]
     *      Dr. Vorname (ldap)
     *      Vorname von (ldap)
     *      //-------------------
     *    [vorname]
     *      Dr. Nachname (ldap)
     *      [vorname+nachname]
     *      Dr. von (ldap)
     *
     *4: [ldap]
     *  Dr. Vorname von Nachname
     *  Dr. Vorname Zweitname Nachname
     *  [vorname]
     *  Dr. von Nachname (ldap)
     * [nachname]
     *  Dr. Vorname von (ldap)
     *
     *  5: [ldap]
     *  Dr. Vorname Zweitname von Nachname
     *  Dr. Vorname Zweitname Zweitname Nachname
     *
     * [titel]
     * Prof. Frank Supper(ldap)
     * [ldap]
     * Frank von Supper <ldap>
     *
     *
      */

    @Test
    void personHatFalscheTitle(){
        assertThatThrownBy(()-> parser.parse("Professor"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");
        
    }
    @Test
    void personHatKeinLdapUser(){
        assertThatThrownBy(()-> parser.parse("Dr. Frank Supper" ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatFalscheTitleNachnameLdapUser(){
        assertThatThrownBy(()-> parser.parse("Prof. Max Müller (ldap)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Titel ist nicht korrekt");

    }

    @Test
    void personHatVornameNameAdditionUndFamilienNamen(){
        assertThatThrownBy(()-> parser.parse(" Frank von Supper "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatTitleVornameLdapUser(){
        assertThatThrownBy(()-> parser.parse(" Dr. Frank (fsupper)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatTitleVornameNameAdditionNachname(){
        assertThatThrownBy(()-> parser.parse(" Dr. Frank von Supper"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatTitleNameAdditionNachnameLdapUser(){
        assertThatThrownBy(()-> parser.parse(" Dr. von Supper(fsupper)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatTitleVornameZweitenameNameAdditionNachname(){
        assertThatThrownBy(()-> parser.parse(" Dr.Frank Max von Supper"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatTitleVornameZweitenameZweitenameNachnameOhneTitle(){
        assertThatThrownBy(()-> parser.parse(" Dr.Frank Max Dieter Supper"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");

    }
    @Test
    void personHatFalscheTitleVornameNameAdditonNachnameLdapUser(){
        assertThatThrownBy(()-> parser.parse("Prof.Frank von  Supper (fsupper)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Titel ist nicht korrekt");

    }
    @Test
    void personHatTitleVornameNameAdditonNachnameLdpUserOhneLetzteKlammer() {
        assertThatThrownBy(() -> parser.parse("Dr.Frank von  Supper (fsupper"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");
    }
    @Test
    void personHatTitleVornameNameAdditonNachnameLdpUserOhneStartKlammer() {
        assertThatThrownBy(() -> parser.parse("Dr.Frank von  Supper fsupper)"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Person ist nicht vollständig");
    }
    // "Frank Supper ()"
    // "Frank (fsupper)"
    // "Dr. Dr. Frank Supper (fsupper)"
    // "Prof. Frank Supper (fsupper)"
    // "F. Supper (fsupper)"


}
