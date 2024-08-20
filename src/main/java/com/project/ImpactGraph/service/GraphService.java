

package com.project.ImpactGraph.service;

import com.project.ImpactGraph.repository.GraphRepository;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GraphService {

    private final GraphRepository graphRepository;
    private final Driver neo4jDriver;

    @Autowired
    public GraphService(GraphRepository graphRepository, Driver neo4jDriver) {
        this.graphRepository = graphRepository;
        this.neo4jDriver = neo4jDriver;
    }

    public Map<String, Object> getGraphData() {
        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> edges = new ArrayList<>();
        Map<String, Map<String, Object>> nodeMap = new HashMap<>();

        try (Session session = neo4jDriver.session();
             Transaction tx = session.beginTransaction()) {

            String query = "MATCH (n)-[r]->(m) RETURN n, r, m UNION MATCH (n) RETURN n AS n, NULL AS r, NULL AS m";
            Result result = tx.run(query);

            while (result.hasNext()) {
                Record record = result.next();
                var nodeData = record.get("n").asNode();
                var targetNodeData = record.containsKey("m") && !record.get("m").isNull() ? record.get("m").asNode() : null;
                var relationshipData = record.containsKey("r") && !record.get("r").isNull() ? record.get("r").asRelationship() : null;

                if (!nodeMap.containsKey(nodeData.elementId())) {
                    Map<String, Object> node = new HashMap<>();
                    node.put("id", nodeData.elementId());
                    node.put("label", nodeData.get("name").asString());
                    nodeMap.put(nodeData.elementId(), node);
                }

                if (targetNodeData != null && relationshipData != null) {
                    if (!nodeMap.containsKey(targetNodeData.elementId())) {
                        Map<String, Object> targetNode = new HashMap<>();
                        targetNode.put("id", targetNodeData.elementId());
                        targetNode.put("label", targetNodeData.get("name").asString());
                        nodeMap.put(targetNodeData.elementId(), targetNode);
                    }

                    Map<String, Object> edge = new HashMap<>();
                    edge.put("from", nodeData.elementId());
                    edge.put("to", targetNodeData.elementId());
                    edge.put("elementId", relationshipData.elementId());
                    edges.add(edge);
                }
            }

            nodes.addAll(nodeMap.values());
            response.put("nodes", nodes);
            response.put("edges", edges);
        }

        return response;
    }

    public Map<String, Object> getNodeDetails(int id) {
        Map<String, Object> nodeDetails = new HashMap<>();

        try (Session session = neo4jDriver.session();
             Transaction tx = session.beginTransaction()) {

            String query = "MATCH (n) WHERE id(n) = $id RETURN n";
            Result result = tx.run(query, Map.of("id", id));

            if (result.hasNext()) {
                Record record = result.next();
                var node = record.get("n").asNode();

                nodeDetails.put("id", node.elementId());
                nodeDetails.put("properties", node.asMap());
            }
        }

        return nodeDetails;
    }

    public List<Map<String, Object>> findImpactedNodes(int id) {
        List<Map<String, Object>> impactedNodes = new ArrayList<>();

        try (Session session = neo4jDriver.session();
             Transaction tx = session.beginTransaction()) {

            String query = "MATCH (m) -[:DEPENDS_ON*0..]->(n) " +
                    "WHERE ID(n) = $id RETURN DISTINCT m AS impactedNode";
            Result result = tx.run(query, Map.of("id", id));

            while (result.hasNext()) {
                Record record = result.next();
                var impactedNode = record.get("impactedNode").asNode();
                Map<String, Object> impactedNodeData = new HashMap<>();
                impactedNodeData.put("elementId", impactedNode.elementId());
                impactedNodeData.put("properties", impactedNode.asMap());

                impactedNodes.add(impactedNodeData);
            } 
        }

        return impactedNodes;
    }

    @Transactional
    public void deleteRelationship(Long relationshipId) {
        graphRepository.deleteRelationshipById(relationshipId);
    }
}
