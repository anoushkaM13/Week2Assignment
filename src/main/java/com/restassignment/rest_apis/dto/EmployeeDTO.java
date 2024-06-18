package com.restassignment.rest_apis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String fname;
    private String lname;
    private String email;
    private String role;
    private Long dept_id;
    private String department_name;

}
