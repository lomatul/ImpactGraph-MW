package com.project.ImpactGraph.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@AllArgsConstructor
@Document
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private String role;

}
