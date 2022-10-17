package com.example.test3.domain;

public class Person {
    private String firstName;
    private String lastName;
    private String title;
    private String nameAddition;
    private String ldapUser;

    public Person(String firstName, String lastName, String title, String nameAddition, String ldapUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.nameAddition = nameAddition;
        this.ldapUser = ldapUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNameAddition() {
        return nameAddition;
    }

    public void setNameAddition(String nameAddition) {
        this.nameAddition = nameAddition;
    }

    public String getLdapUser() {
        return ldapUser;
    }

    public void setLdapUser(String ldapUser) {
        this.ldapUser = ldapUser;
    }
}
