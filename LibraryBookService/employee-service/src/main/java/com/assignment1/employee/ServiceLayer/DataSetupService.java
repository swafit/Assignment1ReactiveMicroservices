package com.assignment1.employee.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner{
@Autowired
    private EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {

        EmployeeDTO e1 = new EmployeeDTO("John Doe","Librarian","23");

        Flux.just(e1)
                        .flatMap(e -> employeeService.insertEmployee(Mono.just(e))
                            .log(e1.toString()))
                        .subscribe();

    }
}
