package com.project.ImpactGraph.service;

import com.project.ImpactGraph.entity.User;
import com.project.ImpactGraph.repository.UserRepository;
import com.project.ImpactGraph.security.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("User added: " + user);
        return userRepo.save(user);
    }
    public List<User> getAllUsers() {
        logger.info("Fetched all users");
        return userRepo.findAll();
    }

    public User deleteUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Component not found with id: " + id));
        logger.info("User deleted: " + user);
        userRepo.delete(user);
        return user;
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

}
