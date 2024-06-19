package com.restassignment.rest_apis.repository;

import com.restassignment.rest_apis.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM public.employees WHERE dept_id= ?", nativeQuery = true)
    List<Employee> findAllByDepartment(Long id);

    @Query(value = "SELECT COUNT(*) AS count FROM public.employees WHERE email_id = ?", nativeQuery = true)
    int existsByEmail(String email);

    @Query(value = "SELECT COUNT(*) AS count FROM public.department WHERE id = ?", nativeQuery = true)
    int depExists(Long depId);
}
