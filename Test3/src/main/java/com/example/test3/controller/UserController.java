package com.example.test3.controller;

import com.example.test3.domain.Raum;
import com.example.test3.service.ErrorDto;
import com.example.test3.service.RaumService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController

public class UserController {


    private RaumService raumService;

    public UserController(RaumService raumService) {
        this.raumService = raumService;
    }

    @PostMapping(path = "api/import", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})



    public ResponseEntity<Object> importDocument(@RequestPart MultipartFile document) throws IOException {
        if (document == null) {
            // Fehlt im POST-Request das Daten-Feld
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "no csv file given"));
        }
        // TODO: prüfe ob Daten-Feld valide


        String content = new String(document.getBytes());
        return raumService.saveRaum(content);
    }



    @GetMapping("api/room")
    public List<Raum> getRooms() {
        return raumService.getRooms();
    }

    @GetMapping("api/room/{number}")
    public ResponseEntity<Object> getRoom(@PathVariable String number) {
        if (number.length() != 4) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "invalid number format"));
        }
        List<Raum> rooms = raumService.getRooms();
        if (rooms.isEmpty()) {
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "no rooms imported"));
        }
        for (Raum room : rooms) {
            if (Objects.equals(room.getRoom(), number)) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(room);
            }
        }
        return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "room number not found"));
    }


}




