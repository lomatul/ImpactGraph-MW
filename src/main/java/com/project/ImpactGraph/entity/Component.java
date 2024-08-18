package com.project.ImpactGraph.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Node
public class Component {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String ip;

    @Relationship(type = "DEPENDS_ON", direction = Relationship.Direction.INCOMING)
    private List<Component> incomingComponents;

    @Relationship(type = "DEPENDS_ON", direction = Relationship.Direction.OUTGOING)
    private List<Component> outgoingComponents;

    public Component(String name, String type, String ip, List<Component> incomingComponents, List<Component> outgoingComponents) {
        this.name = name;
        this.type = type;
        this.ip = ip;
        this.incomingComponents = incomingComponents;
        this.outgoingComponents = outgoingComponents;
    }


}
