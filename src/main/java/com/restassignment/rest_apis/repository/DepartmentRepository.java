package com.restassignment.rest_apis.repository;

import com.restassignment.rest_apis.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "SELECT COUNT(*) AS count FROM public.department WHERE department_name = ?", nativeQuery = true)
    int existsByName(String name);

    @Query(value = "SELECT COUNT(*) AS count FROM public.employees WHERE dept_id = ?", nativeQuery = true)
    int getEmpCount(Long id);

//    @Query(value = "UPDATE public.department SET team_size = ? WHERE id = ?", nativeQuery = true)
//    void updCount(int tot,  long id);

//    @Query(value = "UPDATE public.department SET team_size = (SELECT COUNT(*) AS count FROM public.employees WHERE dept_id = public.department.id) WHERE id = ? ", nativeQuery = true)
//    int updEmpCount(Long id);
}
