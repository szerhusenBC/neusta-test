package com.example.test3.service;

import com.example.test3.domain.Person;
import com.example.test3.domain.Raum;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RaumService {

    private List<Raum> raeume;
    private Set<String> tempPersonData = new HashSet<>();

    public ResponseEntity<Object> saveRaum(String content) {

        String[] raumInfos = content.split("\n");
        List<Raum> tempRaumList = new ArrayList<>();
        Set<String> tempRaumNummer = new HashSet<>();


        for (String raum : raumInfos) {
            String[] raumSplit = raum.split(",");

            String raumNr = raumSplit[0];
            if (!tempRaumNummer.add(raumNr)) {
                // Räume dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 2 (HTTP 400)
                return ResponseEntity.badRequest().body("Fehlercode 2 HTTP 400");
            }
            try {
                tempRaumList.add(new Raum(raumNr, extractPersonen(raumSplit)));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }

        raeume = tempRaumList;
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(content);
    }

   /* public void saveRaum2(String content) throws Exception{

        String[] raumInfos = content.split("\n");
        List<Raum> tempRaumList = new ArrayList<>();
        Set<String> tempRaumNummer = new HashSet<>();

        for (String raum : raumInfos) {
            String[] raumSplit = raum.split(",");

            String raumNr = raumSplit[0];
            if (!tempRaumNummer.add(raumNr)) {
                // Räume dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 2 (HTTP 400)
                throw new Exception("Fehlercode 2 HTTP 400");
            }
            tempRaumList.add(new Raum(raumNr, extractPersonen(raumSplit)));
        }

        raeume = tempRaumList;

    }*/

    private List<Person> extractPersonen(String[] raumInfos) throws Exception {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < raumInfos.length; i++) {

            Person person = buildPerson(raumInfos[i].trim());
            personList.add(person);
        }

        return personList;
    }

    private Person buildPerson(String userInfo) throws Exception {
        String[] infoSplit = userInfo.split(" ");

        String ldapUser = infoSplit[infoSplit.length - 1].replace("(", "").replace(")", "");
        String lastName = infoSplit[infoSplit.length - 2];
        int firstNamePosition = infoSplit[0].contains(".") ? 1 : 0;
        String titel = infoSplit[0].contains(".") ? "Dr." : "";
        String nameAddition = constructNameAddition(infoSplit[infoSplit.length - 3]);
        String firstName = infoSplit[firstNamePosition];

        if (!tempPersonData.add(firstName + "" + lastName)) {
            // Bewohner dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 3 (HTTP 400)
            throw new Exception("Fehlercode 3 HTTP 400");
        }

        return new Person(firstName, lastName, titel, nameAddition, ldapUser);
    }

    private String constructNameAddition(String s) {
        if (s.equals("von") || s.equals("de") || s.equals("van")) {
            return s;

        }
        return "";
    }


    public List<Raum> alleRaume() {
        if (raeume != null) {
            return raeume;
        }
        return Collections.emptyList();
    }
}




