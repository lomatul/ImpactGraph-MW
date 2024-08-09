package com.project.ImpactGraph.repository;

import com.project.ImpactGraph.entity.Component;
import org.springframework.data.neo4j.repository.Neo4jRepository;


import java.util.Optional;


public interface ComponentRepository extends Neo4jRepository<Component, Long> {

    Component findByName ( String name);

}
