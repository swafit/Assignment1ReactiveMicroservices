package com.assignment1.employee.ServiceLayer;

import org.springframework.stereotype.Service;

@Service
public class DataSetupService implements CmmandLineRunner{
@Autowired
    private EmployeeService employeeService;

    @Override
    public void run(String... args) throws Exception {

        EmployeeDTO p1 = new EmployeeDTO("4k-tv",1000);
        EmployeeDTO p2 = new EmployeeDTO("Graphics card",3000);
        EmployeeDTO p3 = new EmployeeDTO("I7 processor",500);
        EmployeeDTO p4 = new EmployeeDTO("HyperX headset",150);
        EmployeeDTO p5 = new EmployeeDTO("Monitor",350);

        Flux.just(p1,p2,p3,p4,p5)
                .flatMap(p -> productService.insertProduct(Mono.just(p))
                    .log(p1.toString()))
                .subscribe();

    }
}

}
