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
                return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(fehlerCode(400,"invalid number format"));

            }
            if (!tempRaumNummer.add(raumNr)) {
                // Räume dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 2 (HTTP 400)
                return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(fehlerCode(400,"room already exists"));
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



    private List<Person> extractPersonen(String[] raumInfos) throws Exception {
        List<Person> personList = new ArrayList<>();
        for (int i = 1; i < raumInfos.length; i++) {

            Person person = buildPerson(raumInfos[i].trim());
            personList.add(person);
        }

        return personList;
    }




    // [ggf. Titel] Vorname [ggf. Zweitname(n)] [ggf. Namenszusatz] Nachname (LDAP-Username)


    private Person buildPerson(String userInfo) throws Exception {
        List<String> infoSplit = new ArrayList<>(Arrays.asList(userInfo.split(" ")));

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
        if (!tempPersonData.add(firstName + "" + lastName)) {
            // Bewohner dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 3 (HTTP 400)
            throw new Exception (fehlerCode(400,"person already exists"));
        }

        return new Person(firstName, lastName, titel, nameAddition, ldapUser);
    }

    private String constructNameAddition(String s) {
        if (s.equals("von") || s.equals("de") || s.equals("van")) {
            return s;

        }
        return "";
    }


    public List<Raum> getRooms() {
        if (raeume != null) {
            return raeume;
        }
        return Collections.emptyList();
    }

    public String fehlerCode(int httpCode, String message) {
        return "{\n" +
                "   code:   "+httpCode+",\n"+
                "   message:"+message +"\n"+
                "}";
    }
}




