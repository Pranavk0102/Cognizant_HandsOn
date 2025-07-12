package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpringDataEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println("Employee saved with Spring Data JPA: " + savedEmployee);
        return savedEmployee;
    }

    public List<Employee> findAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        System.out.println("Found " + employees.size() + " employees with Spring Data JPA");
        return employees;
    }

    public Optional<Employee> findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            System.out.println("Found employee with Spring Data JPA: " + employee.get());
        } else {
            System.out.println("Employee not found with ID: " + id);
        }
        return employee;
    }

    public List<Employee> findByDepartment(String department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        System.out.println("Found " + employees.size() + " employees in " + department + " department");
        return employees;
    }

    public List<Employee> findBySalaryGreaterThan(Double salary) {
        List<Employee> employees = employeeRepository.findBySalaryGreaterThan(salary);
        System.out.println("Found " + employees.size() + " employees with salary > " + salary);
        return employees;
    }

    public List<Employee> findByFirstNameAndLastName(String firstName, String lastName) {
        List<Employee> employees = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        System.out.println("Found " + employees.size() + " employees with name: " + firstName + " " + lastName);
        return employees;
    }

    public Optional<Employee> findByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if (employee.isPresent()) {
            System.out.println("Found employee by email: " + employee.get());
        } else {
            System.out.println("Employee not found with email: " + email);
        }
        return employee;
    }

    public List<Employee> findByDepartmentOrderBySalaryDesc(String department) {
        List<Employee> employees = employeeRepository.findByDepartmentOrderBySalaryDesc(department);
        System.out.println("Found " + employees.size() + " employees in " + department + " ordered by salary desc");
        return employees;
    }

    public boolean existsByEmail(String email) {
        boolean exists = employeeRepository.existsByEmail(email);
        System.out.println("Employee exists with email " + email + ": " + exists);
        return exists;
    }

    public long countByDepartment(String department) {
        long count = employeeRepository.countByDepartment(department);
        System.out.println("Number of employees in " + department + ": " + count);
        return count;
    }

    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            System.out.println("Employee deleted with ID: " + id);
        } else {
            System.out.println("Employee not found with ID: " + id);
        }
    }

    public void deleteByEmail(String email) {
        long deletedCount = employeeRepository.deleteByEmail(email);
        System.out.println("Deleted " + deletedCount + " employee(s) with email: " + email);
    }

    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.save(employee);
        System.out.println("Employee updated with Spring Data JPA: " + updatedEmployee);
        return updatedEmployee;
    }

    // Custom query methods using @Query annotation
    public List<Employee> findEmployeesBySalaryRange(Double minSalary, Double maxSalary) {
        List<Employee> employees = employeeRepository.findEmployeesBySalaryRange(minSalary, maxSalary);
        System.out.println("Found " + employees.size() + " employees with salary between " + minSalary + " and " + maxSalary);
        return employees;
    }

    public List<Employee> findEmployeesByDepartmentAndSalary(String department, Double minSalary) {
        List<Employee> employees = employeeRepository.findEmployeesByDepartmentAndSalary(department, minSalary);
        System.out.println("Found " + employees.size() + " employees in " + department + " with salary >= " + minSalary);
        return employees;
    }
}