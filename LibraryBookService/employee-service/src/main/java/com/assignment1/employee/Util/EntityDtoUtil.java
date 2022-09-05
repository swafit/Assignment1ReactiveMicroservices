package com.assignment1.employee.Util;

import com.assignment1.employee.DataAccessLayer.Employee;
import com.assignment1.employee.ServiceLayer.EmployeeDTO;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

public class EntityDtoUtil {

     public static EmployeeDTO toDTO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, dto);
        return dto;
    }

    public static Employee toEntity (EmployeeDTO dto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto, employee);
        return employee;
    }

    public static String generateUUIDString(){
        return UUID.randomUUID().toString();
    }

}
