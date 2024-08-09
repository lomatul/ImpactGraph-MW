package com.project.ImpactGraph.controller;

import com.project.ImpactGraph.dto.ComponentDTO;
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

    @PostMapping(path = "/create")
    public void createComponent(@RequestBody ComponentDTO request) {
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

    @GetMapping(path = "/all")
    public List<ComponentDTO> getAllComponents() {
        return componentService.getAllComponents();
    }

    @GetMapping(path = "/ComID/{id}")
    public Component getComponentById(@PathVariable long id) {
        return componentService.getComponentByID(id);
    }

    @GetMapping(path = "/ComName/{name}")
    public Component getComponentByName(@PathVariable String name) {
        return componentService.getComponentByName(name);
    }
    @PutMapping(path = "/update")
    public Component updateComponent(@RequestBody ComponentDTO request) {
        // Fetch the existing component by ID
        Component existingComponent = componentService.getComponentByID(request.getId());

        // Update the existing component's fields
        existingComponent.setName(request.getName());
        existingComponent.setIp(request.getIp());
        existingComponent.setType(request.getType());

        // Update the incoming and outgoing node IDs
        return componentService.updateComponent(
                existingComponent,
                request.getIncomingNodeIds(),
                request.getOutgoingNodeIds()
        );
    }
}