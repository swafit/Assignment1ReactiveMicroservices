package com.assignment1.employee.DataAccessLayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>
{
    Mono<Employee> findEmployeeByEmployeeUUID(String employeeUUIDString);
    Mono<Void> deleteEmployeeByEmployeeUUID(String employeeUUIDString);
//    boolean existsEmployeeByEmployeeUUID(String employeeUUIDString);
//    boolean existsEmployeeByLibraryUUID(String libraryUUIDString);
    Flux<Employee> findEmployeesByLibraryUUID(String libraryUUIDString);

}
