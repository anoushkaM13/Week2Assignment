package com.restassignment.rest_apis.service;

import com.restassignment.rest_apis.dto.EmployeeDTO;
import com.restassignment.rest_apis.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getEmployeeByDepartment(Long departmentId);

    Employee getEmpById(Long empId);

    List<Employee> getAllEmp();

    Employee updateEmp(Long empId, Employee updatedEmpDet);

    void deleteEmp(Long empId);
}
