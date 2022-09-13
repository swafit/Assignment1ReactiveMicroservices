package com.assignment1.employee.ServiceLayer;

import com.assignment1.employee.DataAccessLayer.EmployeeRepository;
import com.assignment1.employee.Util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository repository;

    @Override
    public Flux<EmployeeDTO> getAll(){
        return repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<EmployeeDTO> insertEmployee(Mono<EmployeeDTO> employeeDTOMono) {
        return employeeDTOMono
                .map(EntityDtoUtil::toEntity)
                .doOnNext(e-> e.setEmployeeUUID(EntityDtoUtil.generateUUIDString()))
                .flatMap(repository::insert)
                .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<EmployeeDTO> updateEmployee(String employeeUUIDString, Mono<EmployeeDTO> employeeDTOMono) {
//        if(!repository.existsEmployeeByEmployeeUUID(employeeUUIDString)) {
//                    System.out.println("NotFoundThrown");
//                    throw new NotFoundException("Unknown EmployeeUUID provided: " + employeeUUIDString);
//                    }
        return repository.findEmployeeByEmployeeUUID(employeeUUIDString)
                .flatMap(p -> employeeDTOMono
                        .map(EntityDtoUtil::toEntity)
                        .doOnNext(e->e.setEmployeeUUID(p.getEmployeeUUID()))
                        .doOnNext(e -> e.setId(p.getId()))
                )
                .flatMap(repository::save)
                .map(EntityDtoUtil::toDto);

    }
    @Override
    public Flux<EmployeeDTO> getEmployeesByLibraryUUIDString(String libraryUUIDString) {
//        if(!repository.existsEmployeeByLibraryUUID(libraryUUIDString)) {
//            System.out.println("NotFoundThrown");
//            throw new NotFoundException("Unknown LibraryUUID provided: " + libraryUUIDString);
//            }
            return repository.findEmployeesByLibraryUUID(libraryUUIDString)
             .map(EntityDtoUtil::toDto);
        }

    @Override
    public Mono<EmployeeDTO> getEmployeeByEmployeeUUIDString(String employeeUUIDString) {
            /*if(!repository.existsEmployeeByEmployeeUUID(employeeUUIDString)) {
                        System.out.println("NotFoundThrown");
                        throw new NotFoundException("Unknown EmployeeUUID provided: " + employeeUUIDString);
            */
        return repository.findEmployeeByEmployeeUUID(employeeUUIDString)
         .map(EntityDtoUtil::toDto);
    }

    @Override
    public Mono<Void> deleteEmployeeByEmployeeUUID(String employeeUUIDString) {

        return repository.deleteEmployeeByEmployeeUUID(employeeUUIDString);
    }
}
