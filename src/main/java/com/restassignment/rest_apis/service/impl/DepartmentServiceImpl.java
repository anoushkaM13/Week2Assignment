package com.restassignment.rest_apis.service.impl;

import com.restassignment.rest_apis.entity.Department;
import com.restassignment.rest_apis.exception.RecordNotFound;
import com.restassignment.rest_apis.repository.DepartmentRepository;
import com.restassignment.rest_apis.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Override
    public Department createDepartment(Department department) {
        Department dept = departmentRepository.save(department);
        return dept;
    }

    @Override
    public Department updateDepartment(Long depId, Department dept) {
//        Department department = departmentRepository.findById(depId).orElseThrow(()-> new RecordNotFound("Department Does Not Exist id:" + depId));
        return null;
    }

    @Override
    public List<Department> getAllDep() {
        return List.of();
    }
}
