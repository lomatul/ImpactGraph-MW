package com.project.ImpactGraph.controller;

import java.util.List;

import com.project.ImpactGraph.entity.User;
import com.project.ImpactGraph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public User deleteUser(@PathVariable Long id) {
       return userService.deleteUserById(id);
    }


    @GetMapping("/checkUsername/{username}")
    public Boolean checkUsernameExists(@PathVariable String username) {
        boolean exists = userService.existsByUsername(username);
        return exists;
    }
}
