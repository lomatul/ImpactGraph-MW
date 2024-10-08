package com.project.ImpactGraph.controller;

import com.project.ImpactGraph.dto.ComponentDTO;
import com.project.ImpactGraph.entity.Component;
import com.project.ImpactGraph.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(path = "/ComID/{id}")
    public Component getComponentById(@PathVariable long id) {
        return componentService.getComponentByID(id);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping(path = "/all")
    public List<ComponentDTO> getAllComponents() {
        return componentService.getAllComponents();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/delete/{id}")
    public Component getComponentByName(@PathVariable Long id) {
        return componentService.deleteComponentByID(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/update")
    public Component updateComponent(@RequestBody ComponentDTO request) {
        Component existingComponent = componentService.getComponentByID(request.getId());

        existingComponent.setName(request.getName());
        existingComponent.setIp(request.getIp());
        existingComponent.setType(request.getType());

        return componentService.updateComponent(
                existingComponent,
                request.getIncomingNodeIds(),
                request.getOutgoingNodeIds()
        );
    }


}