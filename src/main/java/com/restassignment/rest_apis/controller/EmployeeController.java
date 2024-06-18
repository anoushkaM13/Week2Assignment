package com.restassignment.rest_apis.controller;

import com.restassignment.rest_apis.dto.EmployeeDTO;
import com.restassignment.rest_apis.entity.Employee;
import com.restassignment.rest_apis.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/employee/crud")
public class EmployeeController {

    private EmployeeService employeeService;
    //inserts new employee record into employee table, parameters : fname, lname, email, department_id, role
    @PostMapping("/insert")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmp = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable("id") Long empId){
        Employee emp = employeeService.getEmpById(empId);
        return ResponseEntity.ok(emp);
    }

    @GetMapping("/getByDepartment/{id}")
    public ResponseEntity<List<EmployeeDTO>> getByDept(@PathVariable("id") Long dId){
        List<EmployeeDTO> empsD = employeeService.getEmployeeByDepartment(dId);
        return ResponseEntity.ok(empsD);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmp(){
        List<Employee> emps = employeeService.getAllEmp();
        return ResponseEntity.ok(emps);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable("id") Long empId, @RequestBody Employee updatedEmpDet){
        Employee emp = employeeService.updateEmp(empId, updatedEmpDet);
        return ResponseEntity.ok(emp);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable("id") Long empId){
        employeeService.deleteEmp(empId);
        return ResponseEntity.ok("Employee Deleted SuccessFully");
    }
}
