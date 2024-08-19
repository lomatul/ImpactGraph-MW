package com.project.ImpactGraph.service;

import com.project.ImpactGraph.repository.ComponentRepository;
import com.project.ImpactGraph.repository.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GraphService {

    private final GraphRepository graphRepository;


    @Autowired
    public GraphService(GraphRepository graphRepository) {
        this.graphRepository = graphRepository;
    }

    @Transactional
    public void deleteRelationship(Long relationshipId) {
        graphRepository.deleteRelationshipById(relationshipId);
    }
}

