package com.project.ImpactGraph.service;

import com.project.ImpactGraph.entity.Component;
import com.project.ImpactGraph.entity.User;
import com.project.ImpactGraph.repository.UserRepository;
import org.neo4j.cypherdsl.core.Use;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        // Encode the plain password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Save the user to the repository
        return userRepo.save(user);
    }
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User deleteUserById(String id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Component not found with id: " + id));
        userRepo.delete(user);
        return user;
    }

}
