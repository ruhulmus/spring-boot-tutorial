package com.ruhulmus.controller;

import com.ruhulmus.dto.user.request.UserDto;
import com.ruhulmus.persistence.entity.User;
import com.ruhulmus.persistence.repository.UserRepository;
import com.ruhulmus.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> list() {
        return userService.list();
    }

    @PostMapping(path = "/add")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto add(@Valid @RequestBody UserDto userdto) {
        return userService.add(userdto);
    }


}
