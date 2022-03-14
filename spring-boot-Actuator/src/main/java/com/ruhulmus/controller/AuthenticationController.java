package com.ruhulmus.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class AuthenticationController {
    @RequestMapping({"/testuri"})
    public String hello() {
        return "Hello World !! Rest Controller is working ...";
    }
}
