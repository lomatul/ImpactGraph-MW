package com.project.ImpactGraph.repository;

import com.project.ImpactGraph.entity.Component;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphRepository extends Neo4jRepository<Component, Long> {

    @Query("MATCH ()-[r]->() WHERE ID(r) = $relationshipId DELETE r")
    void deleteRelationshipById(Long relationshipId);
}