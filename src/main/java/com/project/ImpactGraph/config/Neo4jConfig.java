package com.project.ImpactGraph.config;

import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Neo4jConfig {

    @Bean
    public Driver neo4jDriver() {
        return GraphDatabase.driver("neo4j+s://b93c2f97.databases.neo4j.io",
                org.neo4j.driver.AuthTokens.basic("neo4j", "eLcSToxpFiFbX9W8NG8ZnOs5WkL2PfaGtoDYJMQ0370"));
    }

    // Add this method to configure the Neo4j transaction manager
    @Bean
    public PlatformTransactionManager transactionManager(Driver driver) {
        return new Neo4jTransactionManager(driver);
    }
}
