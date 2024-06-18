package com.restassignment.rest_apis.repository;

import com.restassignment.rest_apis.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM public.employees WHERE dept_id= ?", nativeQuery = true)
    List<Employee> findAllByDepartment(Long id);

//    @Query(value = "INSERT INTO public.employees( id, email_id, first_name, last_name, role, department_name) VALUES (:#{#employee.id}, :#{#employee.emailId}, :#{#employee.firstName}, :#{#employee.lastName}, :#{#employee.role}, :#{#employee.departmentName})", nativeQuery = true)
//    Employee addRecord(@Param("employee") Employee employee);

}
