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
            if (raumNr.length() != 4) {
                return ResponseEntity.badRequest().body("Fehlercode 4 HTTP 400");

            }
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

       /* int size = infoSplit.length;
        switch (size) {
            case 3: {
                String ldapUser = infoSplit[size - 1].replace("(", "").replace(")", "");
                String lastName = infoSplit[size - 2];
                String firstName = infoSplit[size - 3];
                if (!tempPersonData.add(firstName + "" + lastName)) {
                    // Bewohner dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 3 (HTTP 400)
                    throw new Exception("Fehlercode 3 HTTP 400");
                }
                return new Person(firstName, lastName, "", "", ldapUser);
            }
            case 4: {
                String titel = "";
                String firstName = "";
                String nameAddition = "";
                if (infoSplit[0].contains(".")) {
                    titel = infoSplit[0];
                    if (!titel.equals("Dr.")) {
                        // titel is nicht Dr.
                        throw new Exception("Fehlercode 4 HTTP 400");
                    }
                    firstName = infoSplit[size - 3];
                } else if (infoSplit[1].equals("von") || infoSplit[1].equals("de") || infoSplit[1].equals("van")) {
                    nameAddition = infoSplit[1];
                    firstName = infoSplit[0];
                } else {
                    firstName = infoSplit[0] + " " + infoSplit[1];
                }
                String ldapUser = infoSplit[size - 1].replace("(", "").replace(")", "");
                String lastName = infoSplit[size - 2];

                if (!tempPersonData.add(firstName + "" + lastName)) {
                    // Bewohner dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 3 (HTTP 400)
                    throw new Exception("Fehlercode 3 HTTP 400");
                }
                return new Person(firstName, lastName, titel, nameAddition, ldapUser);
            }
            case 5: {
                String titel = "";
                String firstName = "";
                String nameAddition = "";
                if (infoSplit[0].contains(".")) {
                    titel = infoSplit[0];
                    if (!titel.equals("Dr.")) {
                        // titel is nicht Dr.
                        throw new Exception("Fehlercode 4 HTTP 400");
                    }
                    if (infoSplit[3].equals("von") || infoSplit[3].equals("de") || infoSplit[3].equals("van")) {
                        nameAddition = infoSplit[3];
                        firstName = infoSplit[1];
                    }
                    else {
                        firstName = infoSplit[1] + " " + infoSplit[2];
                    }

                }  else {
                    firstName = infoSplit[0] + " " + infoSplit[1];
                    nameAddition = infoSplit[2];
                }
                String ldapUser = infoSplit[size - 1].replace("(", "").replace(")", "");
                String lastName = infoSplit[size - 2];

                if (!tempPersonData.add(firstName + "" + lastName)) {
                    // Bewohner dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 3 (HTTP 400)
                    throw new Exception("Fehlercode 3 HTTP 400");
                }
                return new Person(firstName, lastName, titel, nameAddition, ldapUser);
            }
            case 6: {
                String title = infoSplit[0];
                String ldapUser = infoSplit[size - 1].replace("(", "").replace(")", "");
                String lastName = infoSplit[size - 2];
                String nameAddition = infoSplit[size - 3];
                String firstName = infoSplit[size - 5] + infoSplit[size - 4];
                if (!tempPersonData.add(firstName + "" + lastName)) {
                    // Bewohner dürfen nur einmalig in der Importdatei vorkommen, sonst Fehlercode 3 (HTTP 400)
                    throw new Exception("Fehlercode 3 HTTP 400");
                }
                return new Person(firstName, lastName, title, nameAddition, ldapUser);
            }
            default:
                // CSV Zeile valide (siehe Endpunkt: POST /api/import), sonst Fehlercode 4 (HTTP 400)
                // falsches Format
                throw new Exception("Fehlercode 4 HTTP 400");
        }
*/


    // [ggf. Titel] Vorname [ggf. Zweitname(n)] [ggf. Namenszusatz] Nachname (LDAP-Username)
    private Person buildPerson(String userInfo) throws Exception {
        List<String> infoSplit = Arrays.stream(userInfo.split(" ")).toList();

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
            firstName = firstName + name;
        }
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




