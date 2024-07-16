package com.project.ImpactGraph.controller;

import com.project.ImpactGraph.entity.Component;
import com.project.ImpactGraph.service.ComponentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/components")
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }





}