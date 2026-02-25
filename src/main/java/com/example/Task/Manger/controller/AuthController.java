package com.example.Task.Manger.controller;

import com.example.Task.Manger.model.User;
import com.example.Task.Manger.service.UserService;
import com.example.Task.Manger.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        System.out.println("signup"+user);
        return ResponseEntity.ok(userService.signup(user));

    }

    @Autowired
    private  JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<?> token(@RequestBody User user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())

            );

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        return  ResponseEntity.ok(jwtUtils.generateToken(user.getUsername()));

    }

}
