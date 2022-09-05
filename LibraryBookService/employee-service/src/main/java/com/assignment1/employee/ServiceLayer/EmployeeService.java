package com.assignment1.employee.ServiceLayer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Flux<EmployeeDTO> getAll();
    Mono<EmployeeDTO> insertEmployee(Mono<EmployeeDTO> employeeDTOMono);
    Mono<EmployeeDTO> updateEmployee(String employeeUUIDString, Mono<EmployeeDTO> employeeDTOMono);
    Mono<EmployeeDTO> getEmployeeByEmployeeUUIDString(String employeeUUIDString);
    Mono<Void> deleteEmployeeByEmployeeUUID(String employeeUUIDString);
}
