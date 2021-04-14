package com.bookstore.demo.service;

import com.bookstore.demo.exception.InformationExistException;
import com.bookstore.demo.exception.InformationNotFoundException;
import com.bookstore.demo.model.User;
import com.bookstore.demo.model.request.LoginRequest;
import com.bookstore.demo.model.response.LoginResponse;
import com.bookstore.demo.repository.UserRepository;
import com.bookstore.demo.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void setUserDetailService(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    public User createUser(User userObject) {
        System.out.println("service calling createUser ==>");
        if (!userRepository.existsByEmailAddress(userObject.getEmailAddress())) {
            userObject.setPassword(passwordEncoder.encode(userObject.getPassword()));
            return userRepository.save(userObject);
        } else {
            throw new InformationExistException("user with email address " + userObject.getEmailAddress() +
                    " already exists");
        }
    }

    //User login business logic
    public ResponseEntity<Object> loginUser(LoginRequest loginRequest){
        System.out.println("service calling loginUser ==>");
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername((loginRequest.getEmail()));
            final String JWT = jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new LoginResponse(JWT));
        }catch(NullPointerException e){
            throw new InformationNotFoundException("user with that email address " + loginRequest.getEmail() + " not found");
        }
    }

    public User findUserByEmailAddress(String email){
        return userRepository.findUserByEmailAddress(email);
    }
}
