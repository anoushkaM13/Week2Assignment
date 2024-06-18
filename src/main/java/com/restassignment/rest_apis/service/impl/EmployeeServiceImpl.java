package com.restassignment.rest_apis.service.impl;

import com.restassignment.rest_apis.dto.EmployeeDTO;
import com.restassignment.rest_apis.entity.Employee;
import com.restassignment.rest_apis.exception.InvalidPayload;
import com.restassignment.rest_apis.exception.RecordNotFound;
import com.restassignment.rest_apis.repository.EmployeeRepository;
import com.restassignment.rest_apis.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.hibernate.TransientPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.restassignment.rest_apis.mapper.EmployeeMapper.mapToEmployee;
import static com.restassignment.rest_apis.mapper.EmployeeMapper.mapToEmployeeDTO;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee emp = mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(emp);
        return mapToEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getEmployeeByDepartment(Long departmentId) {
        List<Employee> emps = employeeRepository.findAllByDepartment(departmentId);
        return emps.stream().map((employee) -> mapToEmployeeDTO(employee)).collect(Collectors.toList());
    }

    @Override
    public Employee getEmpById(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RecordNotFound("Employee Not Found id:" + empId));
        return employee;
    }

    @Override
    public List<Employee> getAllEmp() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee updateEmp(Long empId, Employee updatedEmpDet) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RecordNotFound("Employee Does Not Exist id:" + empId));

        employee.setFname(updatedEmpDet.getFname());
        employee.setLname(updatedEmpDet.getLname());
        employee.setEmail(updatedEmpDet.getEmail());
        employee.setRole(updatedEmpDet.getRole());
        employee.setDepartment(updatedEmpDet.getDepartment());

        Employee updEmp = employeeRepository.save(employee);

        return updEmp;
    }

    @Override
    public void deleteEmp(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RecordNotFound("Employee Does Not Exist id:" + empId));

        employeeRepository.deleteById(empId);
    }


}
