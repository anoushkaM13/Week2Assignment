package com.restassignment.rest_apis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "department")
public class Department {
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "department_name", nullable = false, unique = true)
    private String department_name;

    @Column(name = "team_size")
    private long team_size;

}
