package com.project.ImpactGraph.service;


import com.project.ImpactGraph.dto.CreateComponentDTO;
import com.project.ImpactGraph.dto.SimpleComponentDTO;
import com.project.ImpactGraph.entity.Component;
import com.project.ImpactGraph.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class ComponentService {

    private final ComponentRepository componentRepository;


    @Autowired
    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Transactional
    public Component createComponent(Component component, List<Long> incomingNodeIds, List<Long> outgoingNodeIds) {

        if (!incomingNodeIds.isEmpty()) {
            component.setIncomingComponents(fetchComponentsByIds(incomingNodeIds));

        } else {
            component.setIncomingComponents(List.of());

        }
        if (!outgoingNodeIds.isEmpty()) {
            component.setOutgoingComponents(fetchComponentsByIds(outgoingNodeIds));


        } else {

            component.setOutgoingComponents(List.of());

        }

        return componentRepository.save(component);
    }

    private List<Component> fetchComponentsByIds(List<Long> ids) {
        return ids.stream()
                .map(id -> componentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Component not found with id: " + id)))
                .collect(Collectors.toList());
    }


    @Transactional
    public List<SimpleComponentDTO> getAllSimpleComponents() {
        List<Component> components = componentRepository.findAll();
        return components.stream()
                .map(this::convertToSimpleComponentDto)
                .collect(Collectors.toList());
    }

    private SimpleComponentDTO convertToSimpleComponentDto(Component component) {
        SimpleComponentDTO dto = new SimpleComponentDTO();
        dto.setId(component.getId());
        dto.setName(component.getName());
        dto.setType(component.getType());
        dto.setIp(component.getIp());
        return dto;
    }
}

