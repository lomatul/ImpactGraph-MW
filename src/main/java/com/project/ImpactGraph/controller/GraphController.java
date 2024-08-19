package com.project.ImpactGraph.controller;

import com.project.ImpactGraph.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/graph")
@CrossOrigin(origins = "http://localhost:3000")
public class GraphController {

    private final GraphService graphService;

    @Autowired
    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/data")
    public Map<String, Object> getGraphData() {
        return graphService.getGraphData();
    }

    @GetMapping("/node/{id}")
    public Map<String, Object> getNodeDetails(@PathVariable int id) {
        return graphService.getNodeDetails(id);
    }

    @GetMapping("/impact/{id}")
    public List<Map<String, Object>> findImpactedNodes(@PathVariable int id) {
        return graphService.findImpactedNodes(id);
    }

    @DeleteMapping("/edge/{id}")
    public void deleteEdge(@PathVariable Long id) {
        graphService.deleteRelationship(id);
    }
}

