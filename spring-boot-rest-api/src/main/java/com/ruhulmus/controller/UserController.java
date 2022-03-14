package com.ruhulmus.controller;

import com.ruhulmus.dto.user.request.User;
import com.ruhulmus.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @PostMapping(path = "/add")
    public String add(@Valid @RequestBody User user) {
        System.out.println("user");
        return "test";
    }


}
