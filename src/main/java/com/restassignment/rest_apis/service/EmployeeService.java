package com.restassignment.rest_apis.service;

import com.restassignment.rest_apis.dto.EmployeeDTO;
import com.restassignment.rest_apis.entity.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getEmployeeByDepartment(Long departmentId);

    EmployeeDTO getEmpById(Long empId);

    List<EmployeeDTO> getAllEmp();

    EmployeeDTO updateEmp(Long empId, EmployeeDTO updatedEmpDet);

    void deleteEmp(Long empId);
}
