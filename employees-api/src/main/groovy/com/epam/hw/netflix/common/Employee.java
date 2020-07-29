package com.epam.hw.netflix.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String workspaceId;
}
