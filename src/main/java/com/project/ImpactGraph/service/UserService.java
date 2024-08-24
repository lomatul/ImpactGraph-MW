package com.project.ImpactGraph.service;

import com.project.ImpactGraph.entity.User;
import com.project.ImpactGraph.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
