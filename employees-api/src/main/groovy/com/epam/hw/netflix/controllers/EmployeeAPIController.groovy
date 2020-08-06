package com.epam.hw.netflix.controllers

import com.epam.hw.netflix.common.WorkspaceAPI
import com.epam.hw.netflix.services.EmployeeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/employees")
class EmployeeAPIController {


    @Value('${app.topic}')
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    EmployeeService employeeService

    @Autowired
    WorkspaceAPI workspaceAPIClient

    Logger log = LoggerFactory.getILoggerFactory().getLogger(EmployeeAPIController.class)

    @RequestMapping("/{id}")
    def describeEmployee(@PathVariable("id") String id) {
        def employee = employeeService.findEmployee(id)
        [
                id       : employee.id,
                firstName: employee.firstName,
                lastName : employee.lastName,
                email    : employee.email,
                workspace: workspaceAPIClient.getWorkspaceById(id) // null? Nope. Let's request exact workspace by employee.workspaceId from workspaces-api. How? With feign client maybe?
        ]

        log.debug(employee.firstName + " " + employee.getEmail() + " " + employee.lastName + " " + employee.workspaceId);
        kafkaTemplate.send(topic, employee.firstName + " " + employee.getEmail() + " " + employee.lastName + " " + employee.workspaceId)
    }
}
