package com.bookstore.demo.controller;

import com.bookstore.demo.model.User;
import com.bookstore.demo.model.request.LoginRequest;
import com.bookstore.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;


    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    //http://localhost/9092//auth/users
    @PostMapping("/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("calling createUser ==>");
        return userService.createUser(userObject);
    }

    //Login method for the user which will authenticate
    //http://localhost/9092/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest){
        System.out.println("calling loginUser ==>");
        return userService.loginUser(loginRequest);
    }
}
