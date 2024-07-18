package com.project.ImpactGraph.controller;

import com.project.ImpactGraph.dto.CreateComponentDTO;
import com.project.ImpactGraph.dto.SimpleComponentDTO;
import com.project.ImpactGraph.entity.Component;
import com.project.ImpactGraph.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/components")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping(path = "{create}")
    public void createComponent(@RequestBody CreateComponentDTO request) {
        Component component = new Component();
        component.setName(request.getName());
        component.setIp(request.getIp());
        component.setType(request.getType());

        componentService.createComponent(
                component,
                request.getIncomingNodeIds(),
                request.getOutgoingNodeIds()
        );

    }

    @GetMapping(path = "{all}")
    public List<SimpleComponentDTO> getAllSimpleComponents(){
        return componentService.getAllSimpleComponents();

    }
}