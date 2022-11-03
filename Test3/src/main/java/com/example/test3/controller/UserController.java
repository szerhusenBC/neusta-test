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

@RestController

public class UserController {


    private  final RaumService raumService;

    public UserController(RaumService raumService) {
        this.raumService = raumService;
    }

    @PostMapping(path = "api/import", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> importDocument(@RequestPart MultipartFile document) throws IOException {
        if (document == null || document.isEmpty()) {
            // Fehlt im POST-Request das Daten-Feld
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "no csv file given"));
        }

        String content = new String(document.getBytes());
        return raumService.saveRaum(content);
    }



    @GetMapping("api/room")
    public List<Raum> getRooms() {

        return raumService.getRooms();
    }

    @GetMapping("api/room/{number}")
    public ResponseEntity getRoom(@PathVariable String number) {

        return raumService.getRoom(number);
    }


}




