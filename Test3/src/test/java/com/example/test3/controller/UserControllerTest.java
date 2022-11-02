package com.example.test3.controller;

import com.example.test3.domain.Raum;
import com.example.test3.service.ErrorDto;
import com.example.test3.service.PersonParser;
import com.example.test3.service.RaumService;
import org.assertj.core.internal.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;



class UserControllerTest {


    private final RaumService raumService=mock (RaumService.class);

    private final Raum raum = mock (Raum.class);

    private final UserController userController = new UserController(new RaumService());


    @Test
    void importDocumentNull() throws IOException {
        // ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "no csv file given"));
        ResponseEntity response = userController.importDocument(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto errorDto = (ErrorDto) response.getBody();
        assertEquals(400, errorDto.getCode());
        assertEquals("no csv file given", errorDto.getMessage());
    }
    //importDocumentEmpty,
    @Test
    void importDocumentEmpty() throws IOException {
        // ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body(new ErrorDto(400, "no csv file given"));
        ResponseEntity response = userController.importDocument(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto errorDto = (ErrorDto) response.getBody();
        assertEquals(400, errorDto.getCode());
        assertEquals("no csv file given", errorDto.getMessage());
    }


    @Test
    void importDocumentWrongRaumNr() throws IOException {
        MultipartFile multipartFile = new MockMultipartFile("sitzplanWrongRaumNr.csv", new FileInputStream(new File("src/test/resources/sitzplanWrongRaumNr.csv")));
        ResponseEntity response = userController.importDocument(multipartFile);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ErrorDto errorDto = (ErrorDto) response.getBody();
        assertEquals(400, errorDto.getCode());
        assertEquals("invalid number format", errorDto.getMessage());
    }
    //importDocumentNotUniqueNr
    //importDocumentInvalidPerson
    //importDocumentValid

    @Test
    void getRoomsBeforeImport() {
        List<Raum> response = userController.getRooms();
        assertTrue(response.isEmpty());
    }

    @Test
    void getRoomsAfterImport() {
        //todo: import mit richtigen daten
        List<Raum> response = userController.getRooms();
        assertEquals(15, response.size());
    }

    @Test
    void getRoomInvalidRoomNumber() {

    }

    @Test
    void getRoomByEmptyData() {

    }

    @Test
    void getRoomByValid() {

    }

    @Test
    void getRoomNotFound() {

    }
}