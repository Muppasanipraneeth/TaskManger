package com.example.Task.Manger.controller;

import com.example.Task.Manger.model.User;
import com.example.Task.Manger.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        System.out.println(user+"user");
        return ResponseEntity.ok(userService.signup(user));

    }

}
