package com.restassignment.rest_apis.repository;

import com.restassignment.rest_apis.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
