package com.degenCoders.loliSimpErp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class randomController {

    @GetMapping(value = "/student/{studentId}")
    public String getTestData(@PathVariable Integer studentId) {
        String student = "aaa";

        return student;
    }
}