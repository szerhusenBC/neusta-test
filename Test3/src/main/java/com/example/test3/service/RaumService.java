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
        tempPersonData.clear();

        String[] raumInfos = content.split("\n");
        List<Raum> tempRaumList = new ArrayList<>();
        Set<String> tempRaumNummer = new HashSet<>();


        for (String raum : raumInfos) {
            String[] raumSplit = raum.split(",");

            String raumNr = raumSplit[0];
            if (raumNr.length() != 4) {
                return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "invalid number format"));

            }
            if (!tempRaumNummer.add(raumNr)) {
                // Räume dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 2 (HTTP 400)
                return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "room already exists"));
            }
            try {
                tempRaumList.add(new Raum(raumNr, extractPersonen(raumSplit)));
            } catch (PersonAlreadyExistsException e) {
                return ResponseEntity.badRequest().body(e.getErrorDto());
            }

        }

        raeume = tempRaumList;
        return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(content);
    }


    private List<Person> extractPersonen(String[] raumInfos) {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < raumInfos.length; i++) {

            Person person = buildPerson(raumInfos[i].trim());
            personList.add(person);
        }

        return personList;
    }


    // [ggf. Titel] Vorname [ggf. Zweitname(n)] [ggf. Namenszusatz] Nachname (LDAP-Username)


    private Person buildPerson(String userInfo) {
        Person person = new PersonParser().parse(userInfo);

        if (!tempPersonData.add(person.getFirstName() + "" + person.getLastName())) {
            // Bewohner dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 3 (HTTP 400)
            throw new PersonAlreadyExistsException(new ErrorDto(400, "person already exist"));
        }

        return person;
    }




    public List<Raum> getRooms() {
        if (raeume != null) {
            return raeume;
        }
        return Collections.emptyList();
    }

   /* public String fehlerCode(int httpCode, String message) {
        return "{\n" +
                "   code:   " + httpCode + ",\n" +
                "   message:" + message + "\n" +
                "}";*/
}





