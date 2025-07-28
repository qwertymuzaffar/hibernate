package com.hibernate.controller;

import java.util.List;

import com.hibernate.dto.UserRequest;
import com.hibernate.entity.Profile;
import com.hibernate.entity.User;
import com.hibernate.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to create a user with a profile
    @PostMapping("/createUser")
    public String createUser(@RequestBody UserRequest userRequest) {
        // Create User and Profile based on the input
        User user = new User(userRequest.getUsername(), userRequest.getEmail());
        Profile profile = new Profile(userRequest.getProfileBio(), user);
        user.setProfile(profile);

        // Save user with profile using Hibernate
        userService.saveUserWithProfile(user);
        return "User and profile created successfully!";
    }

    // Endpoint to get a user by ID
    @GetMapping("/getUser/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }
}
