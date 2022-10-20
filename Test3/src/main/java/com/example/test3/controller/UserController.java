package com.example.test3.controller;

import com.example.test3.domain.Raum;
import com.example.test3.service.RaumService;
import org.springframework.http.MediaType;
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
    public void importDocument(@RequestPart MultipartFile document) throws IOException {
        String content = new String(document.getBytes());
        raumService.saveRaum(content);

    }

    @GetMapping("api/room")
    public List<Raum> getRooms() {
        return raumService.getRooms();
    }

    @GetMapping("api/room/{number}")
    public Raum getRoom(@PathVariable String number) {
        List<Raum> rooms = raumService.getRooms();
        for (Raum room : rooms) {
            if (Objects.equals(room.getRoom(), number)){
                return room;

                //if (number.length() != 4) {
                  //  return ResponseEntity.badRequest().body("Fehlercode 4 HTTP 400");
                //}
            }
        }
        return null;
    }
}



