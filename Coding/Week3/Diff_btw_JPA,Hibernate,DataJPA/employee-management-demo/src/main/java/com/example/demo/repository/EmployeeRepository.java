package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Query methods using method name conventions
    List<Employee> findByDepartment(String department);

    List<Employee> findBySalaryGreaterThan(Double salary);

    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartmentOrderBySalaryDesc(String department);

    boolean existsByEmail(String email);

    long countByDepartment(String department);

    long deleteByEmail(String email);

    // Custom queries using @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findEmployeesBySalaryRange(@Param("minSalary") Double minSalary,
                                              @Param("maxSalary") Double maxSalary);

    @Query("SELECT e FROM Employee e WHERE e.department = :department AND e.salary >= :minSalary")
    List<Employee> findEmployeesByDepartmentAndSalary(@Param("department") String department,
                                                      @Param("minSalary") Double minSalary);

    // Native SQL query example
    @Query(value = "SELECT * FROM employees WHERE hire_date >= ?1", nativeQuery = true)
    List<Employee> findEmployeesHiredAfter(java.sql.Date hireDate);

    // Query with aggregate functions
    @Query("SELECT e.department, AVG(e.salary) FROM Employee e GROUP BY e.department")
    List<Object[]> getAverageSalaryByDepartment();

    // Count query
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :department")
    long countEmployeesByDepartment(@Param("department") String department);
}