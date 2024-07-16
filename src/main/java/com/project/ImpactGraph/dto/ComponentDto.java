package com.project.ImpactGraph.dto;

import java.util.List;

public class ComponentDto {
    private String name;
    private String ip;
    private String type;
    private List<Long> incomingNodeIds;
    private List<Long> outgoingNodeIds;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getIncomingNodeIds() {
        return incomingNodeIds;
    }

    public void setIncomingNodeIds(List<Long> incomingNodeIds) {
        this.incomingNodeIds = incomingNodeIds;
    }

    public List<Long> getOutgoingNodeIds() {
        return outgoingNodeIds;
    }

    public void setOutgoingNodeIds(List<Long> outgoingNodeIds) {
        this.outgoingNodeIds = outgoingNodeIds;
    }
}