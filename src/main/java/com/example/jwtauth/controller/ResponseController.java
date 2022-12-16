package com.example.jwtauth.controller;

import com.example.jwtauth.response.AccessDenied;
import com.example.jwtauth.response.AccessGranted;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequestMapping("/api")
public class ResponseController {

    @GetMapping(path = {"/get"})
    public ResponseEntity acceptRequest(){
        return new ResponseEntity<>(new AccessGranted(), ACCEPTED);
    }

    @PostMapping(path = { "/post"})
    public ResponseEntity acceptPost(){
        return new ResponseEntity<>(new AccessGranted(), ACCEPTED);
    }

    @GetMapping(path = {"/access-denied"})
    public ResponseEntity deny(){
        return new ResponseEntity<>(new AccessDenied(), FORBIDDEN);
    }
}
