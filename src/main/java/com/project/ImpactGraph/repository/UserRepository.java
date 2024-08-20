package com.project.ImpactGraph.repository;

import com.project.ImpactGraph.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {

}
