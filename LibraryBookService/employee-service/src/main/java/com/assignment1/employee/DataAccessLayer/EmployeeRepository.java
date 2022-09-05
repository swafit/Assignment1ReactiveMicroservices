package com.assignment1.employee.DataAccessLayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class EmployeeRepository extends ReactiveMongoRepository<Product, String>{
    Mono<Employee> findProductByProductUUID(String productUUIDString);
    Mono<Void> deleteProductByProductUUID(String productUUIDString);
}
