package com.example.test3.domain;

import java.util.List;

public class Raum {
    private String room;
    private List<Person> people;

    public Raum(String nummer,List<Person> bewohner) {
        this.room = nummer;
        this.people = bewohner;

    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}
