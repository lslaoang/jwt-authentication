package com.example.jwtauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.ACCEPTED;

@RestController
@RequestMapping("/api")
public class ResponseController {

    @GetMapping(path = {"/", "/index", "/request"})
    public ResponseEntity acceptRequest(){
        return new ResponseEntity<>("Accepted!", ACCEPTED);
    }
}
