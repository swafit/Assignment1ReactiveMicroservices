package com.assignment1.employee.ServiceLayer;

import com.assignment1.employee.DataAccessLayer.EmployeeRepository;
import com.assignment1.employee.Util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository repository;

    @Override
    public Flux<EmployeeDTO> getAll(){
        return repository.findAll()
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<EmployeeDTO> insertEmployee(Mono<EmployeeDTO> employeeDTOMono) {
        return employeeDTOMono
                .map(EntityDtoUtil::toEntity)
                .doOnNext(e-> e.setEmployeeUUID(EntityDtoUtil.generateUUIDString()))
                .flatMap(repository::insert)
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<EmployeeDTO> updateEmployee(String employeeUUIDString, Mono<EmployeeDTO> employeeDTOMono) {
        return repository.findEmployeeByEmployeeUUID(employeeUUIDString)
                .flatMap(p -> employeeDTOMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e->e.setEmployeeUUID(p.getEmployeeUUID()))
                        .doOnNext(e -> e.setId(p.getId()))
                )
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDTO);

    }

    @Override
    public Mono<EmployeeDTO> getEmployeeByEmployeeUUIDString(String employeeUUIDString) {
        return repository.findEmployeeByEmployeeUUID(employeeUUIDString)
                .map(EntityDtoUtil::toDTO);
    }

    @Override
    public Mono<Void> deleteEmployeeByEmployeeUUID(String employeeUUIDString) {
        return repository.deleteEmployeeByEmployeeUUID(employeeUUIDString);
    }
}
