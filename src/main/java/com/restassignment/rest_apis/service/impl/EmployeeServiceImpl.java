package com.restassignment.rest_apis.service.impl;

import com.restassignment.rest_apis.dto.EmployeeDTO;
import com.restassignment.rest_apis.entity.Department;
import com.restassignment.rest_apis.entity.Employee;
import com.restassignment.rest_apis.exception.DuplicateException;
import com.restassignment.rest_apis.exception.InvalidPayload;
import com.restassignment.rest_apis.exception.RecordNotFound;
import com.restassignment.rest_apis.repository.EmployeeRepository;
import com.restassignment.rest_apis.service.EmployeeService;
import lombok.AllArgsConstructor;
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
        try{
            if(!validateEmployee(employeeDTO)){
                throw new InvalidPayload("The payload provided is invalid");
            } else if (employeeRepository.existsByEmail(employeeDTO.getEmail())>0) {
                throw new DuplicateException("Employee with this email already exists");
            }
            else if(employeeRepository.depExists(employeeDTO.getDept_id())==0){
                throw new RecordNotFound("Department with given id does not exist ");
            }
            else {
                Employee emp = mapToEmployee(employeeDTO);
                Employee savedEmployee = employeeRepository.save(emp);
//                employeeRepository.updEmpCount(employeeDTO.getDept_id());
                return mapToEmployeeDTO(savedEmployee);
            }
        }
        catch(RecordNotFound e){
            throw e;
        }
        catch (InvalidPayload e) {
            throw e;
        } catch (DuplicateException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create employee", e);
        }
    }

    @Override
    public List<EmployeeDTO> getEmployeeByDepartment(Long departmentId) {
        try{
            if(employeeRepository.depExists(departmentId)==0){
                throw new RecordNotFound("Department does not exist id:" + departmentId);
            }
            else{
                List<Employee> emps = employeeRepository.findAllByDepartment(departmentId);
                return emps.stream().map((employee) -> mapToEmployeeDTO(employee)).collect(Collectors.toList());
            }
        }
        catch (RecordNotFound e){
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to get employee", e);
        }
    }

    @Override
    public EmployeeDTO getEmpById(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RecordNotFound("Employee Not Found id:" + empId));
        EmployeeDTO res = mapToEmployeeDTO(employee);
        return res;
    }

    @Override
    public List<EmployeeDTO> getAllEmp() {
        try{
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                throw new RecordNotFound("No Records Found");
            }
            return employees.stream().map((emp) -> mapToEmployeeDTO(emp)).collect(Collectors.toList());
        }
        catch(RecordNotFound e){
            throw e;
        }
    }

    @Override
    public EmployeeDTO updateEmp(Long empId, EmployeeDTO updatedEmpDet) {
        try{
            Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RecordNotFound("Employee Does Not Exist id:" + empId));
            Department department = new Department();
            if(updatedEmpDet.getEmail()!=null){
                if(employeeRepository.existsByEmail(updatedEmpDet.getEmail())>0){
                    throw new DuplicateException("Employee with this email already exists");
                }
                else{
                    employee.setEmail(updatedEmpDet.getEmail());
                }
            }
            if(updatedEmpDet.getFname()!=null){
                employee.setFname(updatedEmpDet.getFname());
            }
            if(updatedEmpDet.getLname()!=null){
                employee.setLname(updatedEmpDet.getLname());
            }
            if(updatedEmpDet.getRole()!=null){
                employee.setRole(updatedEmpDet.getRole());
            }
            if(updatedEmpDet.getDepartment_name()!=null && updatedEmpDet.getDept_id()!=null){
                if(employeeRepository.depExists(updatedEmpDet.getDept_id())==0){
                    throw new RecordNotFound("Department with given id does not exist ");
                }
                department.setDepartment_name(updatedEmpDet.getDepartment_name());
                department.setId(updatedEmpDet.getDept_id());
                employee.setDepartment(department);
//                employeeRepository.updEmpCount(updatedEmpDet.getDept_id()); //updating team size
            }
            Employee updEmp = employeeRepository.save(employee);

            return mapToEmployeeDTO(updEmp);
        }
        catch(RecordNotFound e ){
            throw e;
        }
        catch (DuplicateException e) {
            throw e; // Rethrow DuplicateException
        } catch (Exception e) {
            throw new RuntimeException("Failed to update employee", e); // Catch other exceptions
        }
    }

    @Override
    public void deleteEmp(Long empId) {
        Employee employee = employeeRepository.findById(empId).orElseThrow(()-> new RecordNotFound("Employee Does Not Exist id:" + empId));

        employeeRepository.deleteById(empId);
    }

    public static boolean validateEmployee(EmployeeDTO employee) {
        if (employee.getFname() == null || employee.getFname().isEmpty()) return false;
        if (employee.getLname() == null || employee.getLname().isEmpty()) return false;
        if (employee.getEmail() == null || employee.getEmail().isEmpty()) return false;
        if (employee.getRole() == null || employee.getRole().isEmpty()) return false;
        if (employee.getDept_id() == null) return false;
        if (employee.getDepartment_name() == null || employee.getDepartment_name().isEmpty()) return false;

        return true;
    }


}
