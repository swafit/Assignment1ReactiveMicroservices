package com.assignment1.employee.PresentationLayer;

import com.assignment1.employee.ServiceLayer.EmployeeDTO;
import com.assignment1.employee.ServiceLayer.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("employee")
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;
    
        @GetMapping()
        public Flux<EmployeeDTO> getAllEmployees(){
            return employeeService.getAll();
        }
        @PostMapping()
        public Mono<EmployeeDTO> insertEmployee(@RequestBody Mono<EmployeeDTO> employeeDTOMono){
            return employeeService.insertEmployee(employeeDTOMono);
        }
        @PostMapping("{employeeUUIDString}")
        public Mono</*ResponseEntity<*/EmployeeDTO/*>*/> updateEmployeeByEmployeeUUIDString(@PathVariable String employeeUUIDString, @RequestBody Mono<EmployeeDTO> employeeDTOMono){
            return employeeService.updateEmployee(employeeUUIDString, employeeDTOMono) /* // */;
//                    .map(ResponseEntity::ok)
//                    .defaultIfEmpty(ResponseEntity.notFound().build());
        }
        @GetMapping("{employeeUUIDString}")
        public Mono</*ResponseEntity<*/EmployeeDTO/*>*/> getEmployeeByEmployeeUUID(@PathVariable String employeeUUIDString){
            return employeeService.getEmployeeByEmployeeUUIDString(employeeUUIDString) /* // */;
//                    .map(ResponseEntity::ok)
//                    .defaultIfEmpty(ResponseEntity.notFound().build());
        }
        @GetMapping("/library/{libraryUUIDString}")
        public Flux</*ResponseEntity<*/EmployeeDTO/*>*/> getEmployeeByLibraryUUID(@PathVariable String libraryUUIDString){
            return employeeService.getEmployeesByLibraryUUIDString(libraryUUIDString) /* // */;
//                    .map(ResponseEntity::ok)
//                    .defaultIfEmpty(ResponseEntity.notFound().build());
        }
        @DeleteMapping("{employeeUUIDString}")
        public Mono<Void> deleteEmployeeByEmployeeUUID(@PathVariable String employeeUUIDString){
            return employeeService.deleteEmployeeByEmployeeUUID(employeeUUIDString);
        }
    }
