package com.project.ImpactGraph.config;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfig {

    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver("neo4j+s://b93c2f97.databases.neo4j.io",
                org.neo4j.driver.AuthTokens.basic("neo4j", "eLcSToxpFiFbX9W8NG8ZnOs5WkL2PfaGtoDYJMQ0370"));
    }
}