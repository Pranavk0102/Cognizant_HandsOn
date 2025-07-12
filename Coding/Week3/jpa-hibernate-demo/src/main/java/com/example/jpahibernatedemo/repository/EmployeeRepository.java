package com.example.jpahibernatedemo.repository;

import com.example.jpahibernatedemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Custom query methods using method naming convention
    List<Employee> findByDepartment(String department);
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);
    List<Employee> findBySalaryGreaterThan(Double salary);

    // Custom JPQL query
    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findByEmail(@Param("email") String email);

    // Native SQL query
    @Query(value = "SELECT * FROM employees WHERE department = ?1 ORDER BY salary DESC", nativeQuery = true)
    List<Employee> findByDepartmentOrderBySalaryDesc(String department);
}