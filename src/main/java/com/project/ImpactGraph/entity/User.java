package com.project.ImpactGraph.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.neo4j.core.schema.Id;


@Setter
@Getter
@AllArgsConstructor
@Document
public class User {

    private String name;
    private String password;
    private String role;

}
