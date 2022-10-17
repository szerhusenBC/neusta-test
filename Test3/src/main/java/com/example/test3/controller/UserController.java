package com.example.test3.controller;

import com.example.test3.domain.Raum;
import com.example.test3.service.RaumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private RaumService raumService;

    @GetMapping("/")
    public ResponseEntity getUsers(){
        try {
           return ResponseEntity.ok("Server Leuft");
        } catch (Exception e)   {
            return ResponseEntity.badRequest().body("Fehlercode 2 HTTP ");
        }
    }

    @GetMapping("api/room")
    public List<Raum> alle(){
        return raumService.alleRaume();
    }

    @GetMapping("api/import")
    public ResponseEntity apiImport(){
        try {

            return ResponseEntity.ok("Server");
        } catch (Exception e)   {
            return ResponseEntity.badRequest().body("Fehlercode 2 HTTP 400");
        }
    }

    @RequestMapping(path = "api/import2",method = RequestMethod.POST,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public  ResponseEntity<Object> apiImport(@RequestParam String name,@RequestPart MultipartFile document) throws IOException {
      //  System.out.println(name);
        String content = new String(document.getBytes());
        System.out.println(document.getName());
        return raumService.saveRaum(content);

       /* try {
            raumService.saveRaum2(content);
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(content);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }*/

        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fehlercode 4 HTTP 400");
        //return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(content);
    }
}
