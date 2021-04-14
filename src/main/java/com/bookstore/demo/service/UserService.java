package com.bookstore.demo.service;

import com.bookstore.demo.exception.InformationExistException;
import com.bookstore.demo.model.User;
import com.bookstore.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public User findUserByEmailAddress(String email) {
        return userRepository.findUserByEmailAddress(email);
    }
}
