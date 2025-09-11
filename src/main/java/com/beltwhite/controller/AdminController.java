package com.beltwhite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")

public class AdminController {

    @GetMapping("/teste")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<?> teste() {

        return ResponseEntity.status(200).build();
    }

}
