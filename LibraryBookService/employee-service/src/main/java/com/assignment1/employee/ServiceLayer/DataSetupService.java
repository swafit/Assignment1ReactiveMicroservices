package com.assignment1.employee.ServiceLayer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner{
private final EmployeeService employeeService;

    public DataSetupService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

        EmployeeDTO e1 = new EmployeeDTO("John Doe","Librarian","23");
        EmployeeDTO e2 = new EmployeeDTO("John Doe","Librarian","23");
        EmployeeDTO e3 = new EmployeeDTO("John Doe","Librarian","23");
        EmployeeDTO e4 = new EmployeeDTO("John Doe","Librarian","23");
        EmployeeDTO e5 = new EmployeeDTO("John Doe","Librarian","23");

        Flux.just(e1, e2, e3, e4, e5)
                        .flatMap(e -> employeeService.insertEmployee(Mono.just(e))
                            .log(e1.toString()))
                        .subscribe();

    }
}
