package com.geeksforgeeks.useraccounts.controller;


import com.ashish.exception.NotFoundException;
import com.ashish.common.models.User;
import com.geeksforgeeks.useraccounts.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser  = this.userService.addUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> getUser(@PathVariable UUID userid){
        try {
            User u = this.userService.getUserById(userid);
            return ResponseEntity.ok(u);
        }
        catch(NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
