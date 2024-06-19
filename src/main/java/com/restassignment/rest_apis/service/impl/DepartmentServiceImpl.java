package com.restassignment.rest_apis.service.impl;

import com.restassignment.rest_apis.dto.EmployeeDTO;
import com.restassignment.rest_apis.entity.Department;
import com.restassignment.rest_apis.exception.DatabaseError;
import com.restassignment.rest_apis.exception.DuplicateException;
import com.restassignment.rest_apis.exception.InvalidPayload;
import com.restassignment.rest_apis.exception.RecordNotFound;
import com.restassignment.rest_apis.repository.DepartmentRepository;
import com.restassignment.rest_apis.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    @Override
    public Department createDepartment(Department department) {
        try{
            if(departmentRepository.existsByName(department.getDepartment_name())>0){
                throw new DuplicateException("This department already exists");
            }
            else {
                Department dept = departmentRepository.save(department);
                return dept;
            }
        }
        catch (DuplicateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create department", e);
        }
    }

    @Override
    public Department updateDepartment(Long depId, Department dept) {
        try{
            Department department = departmentRepository.findById(depId).orElseThrow(() -> new RecordNotFound("Department Does Not Exist id:" + depId));
            department.setDepartment_name(dept.getDepartment_name());
            department.setTeam_size(departmentRepository.getEmpCount(depId));
            Department updDep = departmentRepository.save(department);
            return updDep;
        }
        catch (RecordNotFound e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to update Department", e); // Catch other exceptions
        }
    }

    @Override
    public List<Department> getAllDep() {
        List<Department> deps = departmentRepository.findAll();
        return deps;
    }
}
