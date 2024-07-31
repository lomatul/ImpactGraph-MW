package com.project.ImpactGraph.service;


import com.project.ImpactGraph.dto.ComponentDTO;
import com.project.ImpactGraph.entity.Component;
import com.project.ImpactGraph.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
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
    public void createComponent(Component component, List<Long> incomingNodeIds, List<Long> outgoingNodeIds) {

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

         componentRepository.save(component);
    }

    private List<Component> fetchComponentsByIds(List<Long> ids) {
        return ids.stream()
                .map(id -> componentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Component not found with id: " + id)))
                .collect(Collectors.toList());
    }


    @Transactional
    public List<ComponentDTO> getAllComponents() {
        List<Component> components = componentRepository.findAll();
        return components.stream()
                .map(this::convertToComponentDto)
                .collect(Collectors.toList());
    }

    private ComponentDTO convertToComponentDto(Component component) {
        ComponentDTO dto = new ComponentDTO();
        dto.setId(component.getId());
        dto.setName(component.getName());
        dto.setType(component.getType());
        dto.setIp(component.getIp());

        // Map incoming components to their IDs
        if (component.getIncomingComponents() != null) {
            List<Long> incomingNodeIds = component.getIncomingComponents().stream()
                    .map(Component::getId)
                    .collect(Collectors.toList());
            dto.setIncomingNodeIds(incomingNodeIds);
        } else {
            dto.setIncomingNodeIds(Collections.emptyList());
        }

        // Map outgoing components to their IDs
        if (component.getOutgoingComponents() != null) {
            List<Long> outgoingNodeIds = component.getOutgoingComponents().stream()
                    .map(Component::getId)
                    .collect(Collectors.toList());
            dto.setOutgoingNodeIds(outgoingNodeIds);
        } else {
            dto.setOutgoingNodeIds(Collections.emptyList());
        }

        return dto;
    }

}

