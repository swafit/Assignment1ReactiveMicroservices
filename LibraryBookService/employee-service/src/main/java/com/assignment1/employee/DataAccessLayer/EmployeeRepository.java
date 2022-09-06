package com.assignment1.employee.DataAccessLayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>
{
    Mono<Employee> findEmployeeByEmployeeUUID(String employeeUUIDString);
    Mono<Void> deleteEmployeeByEmployeeUUID(String employeeUUIDString);
    boolean existsEmployeeByEmployeeId(String employeeUUIDString);
    boolean existsEmployeeByLibraryUUID(String libraryUUIDString);
    Mono<Employee> findEmployeeByLibraryUUID(String libraryUUIDString);

}
