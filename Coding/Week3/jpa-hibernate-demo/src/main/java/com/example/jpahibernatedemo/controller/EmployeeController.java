package com.example.jpahibernatedemo.controller;

import com.example.jpahibernatedemo.entity.Employee;
import com.example.jpahibernatedemo.service.EmployeeService;
import com.example.jpahibernatedemo.service.EmployeeHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeHibernateService employeeHibernateService;

    // Spring Data JPA Endpoints
    @PostMapping("/jpa")
    public ResponseEntity<Employee> createEmployeeJPA(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/jpa")
    public ResponseEntity<List<Employee>> getAllEmployeesJPA() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/jpa/{id}")
    public ResponseEntity<Employee> getEmployeeByIdJPA(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/jpa/{id}")
    public ResponseEntity<Employee> updateEmployeeJPA(@PathVariable Integer id, @RequestBody Employee employee) {
        Optional<Employee> existingEmployee = employeeService.getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            employee.setId(id);
            Employee updatedEmployee = employeeService.updateEmployee(employee);
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/jpa/{id}")
    public ResponseEntity<Void> deleteEmployeeJPA(@PathVariable Integer id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Hibernate Endpoints
    @PostMapping("/hibernate")
    public ResponseEntity<String> createEmployeeHibernate(@RequestBody Employee employee) {
        Integer employeeId = employeeHibernateService.addEmployee(employee);
        return ResponseEntity.ok("Employee created with ID: " + employeeId);
    }

    @GetMapping("/hibernate")
    public ResponseEntity<List<Employee>> getAllEmployeesHibernate() {
        List<Employee> employees = employeeHibernateService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/hibernate/{id}")
    public ResponseEntity<Employee> getEmployeeByIdHibernate(@PathVariable Integer id) {
        Employee employee = employeeHibernateService.getEmployeeById(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/hibernate/{id}")
    public ResponseEntity<String> updateEmployeeHibernate(@PathVariable Integer id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeHibernateService.updateEmployee(employee);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping("/hibernate/{id}")
    public ResponseEntity<String> deleteEmployeeHibernate(@PathVariable Integer id) {
        employeeHibernateService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    // Test endpoint to create sample data
    @PostMapping("/sample-data")
    public ResponseEntity<String> createSampleData() {
        Employee emp1 = new Employee("John", "Doe", "john.doe@example.com", "IT", 75000.0, LocalDate.of(2020, 1, 15));
        Employee emp2 = new Employee("Jane", "Smith", "jane.smith@example.com", "HR", 65000.0, LocalDate.of(2019, 3, 20));
        Employee emp3 = new Employee("Bob", "Johnson", "bob.johnson@example.com", "IT", 80000.0, LocalDate.of(2021, 6, 10));

        employeeService.addEmployee(emp1);
        employeeService.addEmployee(emp2);
        employeeService.addEmployee(emp3);

        return ResponseEntity.ok("Sample data created successfully");
    }
}