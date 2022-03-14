package com.ruhulmus.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @GetMapping(value = "/add")
    public String hello() {
        return "Hello World !! Rest Controller is working ...";
    }

//    @PostMapping(path = "/add")
//    public String add(@Valid @RequestBody User user) {
//        System.out.println("user");
//        return "test";
//    }


}
