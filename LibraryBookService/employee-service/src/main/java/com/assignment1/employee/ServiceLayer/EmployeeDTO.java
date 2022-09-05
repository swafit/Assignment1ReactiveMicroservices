package com.assignment1.employee.ServiceLayer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EmployeeDTO {

    private String employeeUUID;
    private String name;
    private String role;


   public EmployeeDTO(String name, String role) {
            this.name = name;
            this.role = role;
    }
}
