package com.project.ImpactGraph.service;


import com.project.ImpactGraph.entity.Component;
import com.project.ImpactGraph.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }



}