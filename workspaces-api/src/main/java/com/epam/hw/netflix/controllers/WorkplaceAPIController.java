package com.epam.hw.netflix.controllers;

import com.epam.hw.netflix.common.Workspace;
import com.epam.hw.netflix.common.WorkspaceAPI;
import com.epam.hw.netflix.services.WorkplaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/workspaces")
@Slf4j
public class WorkplaceAPIController implements WorkspaceAPI {

    @Value("${app.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private WorkplaceService workplaceService;

    @RequestMapping("/{id}")
    public Workspace getWorkspaceById(@PathVariable("id") String id) {
        kafkaTemplate.send(topic, "Instance {} received workspace request" + this);
        log.debug("Instance {} received workspace request", this);
        return workplaceService.findWorkspace(id);
    }
}
