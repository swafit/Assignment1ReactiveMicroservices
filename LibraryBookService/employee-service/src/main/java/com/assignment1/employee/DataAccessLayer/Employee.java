package com.assignment1.employee.DataAccessLayer;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Employee {
    @Id
    private String id;
    private String employeeUUID;
    private String name;
    private String role;
    private String libraryUUID;
}
