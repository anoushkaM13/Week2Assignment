package com.restassignment.rest_apis.service;

import com.restassignment.rest_apis.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment (Department department);
    Department updateDepartment (Long depId, Department dept);
    List<Department> getAllDep();
}
