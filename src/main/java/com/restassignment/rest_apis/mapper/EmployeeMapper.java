package com.restassignment.rest_apis.mapper;

import com.restassignment.rest_apis.dto.EmployeeDTO;
import com.restassignment.rest_apis.entity.Department;
import com.restassignment.rest_apis.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDTO mapToEmployeeDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getFname(),
                employee.getLname(),
                employee.getEmail(),
                employee.getRole(),
                employee.getDepartment().getId(),
                employee.getDepartment().getDepartment_name()
        );
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDto){
        Department department = new Department();
        department.setDepartment_name(employeeDto.getDepartment_name());
        department.setId(employeeDto.getDept_id());
        department.setTeam_size(0);
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFname(),
                employeeDto.getLname(),
                employeeDto.getEmail(),
                employeeDto.getRole(),
                department
        );
    }

}
