package com.restassignment.rest_apis.controller;

import com.restassignment.rest_apis.entity.Department;
import com.restassignment.rest_apis.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/department/crud")
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping("/insert")
    public ResponseEntity<Department> createEmployee(@RequestBody Department dept){
        Department savedDept = departmentService.createDepartment(dept);
        return new ResponseEntity<>(savedDept, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDep(){
        List<Department> deps = departmentService.getAllDep();
        return ResponseEntity.ok(deps);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Department> updDep(@PathVariable("id") Long dId, Department dep){
        Department depUpd = departmentService.updateDepartment(dId, dep);
        return ResponseEntity.ok(depUpd);
    }
}
