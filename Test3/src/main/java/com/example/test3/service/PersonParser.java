package com.example.test3.service;

import com.example.test3.domain.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonParser {

    public Person parse(String userInfo){
        String[] splittetUserInfo = userInfo.split(" ");

        if (splittetUserInfo.length == 1) {
            throw new IllegalArgumentException("Person ist nicht vollständig");
        }

        List<String> infoSplit = new ArrayList<>(Arrays.asList(splittetUserInfo));

        String ldapUser = infoSplit.get(infoSplit.size() - 1).replace("(", "").replace(")", "");
        infoSplit.remove(infoSplit.size() - 1);

        String lastName = infoSplit.get(infoSplit.size() - 1);
        infoSplit.remove(infoSplit.size() - 1);

        String titel;
        if (infoSplit.get(0).contains(".")) {
            titel = "Dr.";
            infoSplit.remove(0);
        } else {
            titel = "";
        }

        String nameAddition = constructNameAddition(infoSplit.get(infoSplit.size() - 1));
        if (!nameAddition.equals("")) {
            infoSplit.remove(infoSplit.size() - 1);
        }
        String firstName = "";

        for (String name : infoSplit) {
            firstName = firstName + " " + name;
            firstName = firstName.trim();

        }

        return new Person(firstName, lastName, titel, nameAddition, ldapUser);
    }

    private String constructNameAddition(String s) {
        if (s.equals("von") || s.equals("de") || s.equals("van")) {
            return s;

        }
        return "";
    }}

