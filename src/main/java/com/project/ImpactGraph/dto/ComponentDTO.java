package com.project.ImpactGraph.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ComponentDTO {
    private Long id;
    private String name;
    private String ip;
    private String type;
    private List<Long> incomingNodeIds;
    private List<Long> outgoingNodeIds;

}