package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.HibernateEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HibernateEmployeeService hibernateEmployeeService;

    // Spring Data JPA endpoints
    @GetMapping("/jpa")
    public ResponseEntity<List<Employee>> getAllEmployeesJPA() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/jpa/{id}")
    public ResponseEntity<Employee> getEmployeeByIdJPA(@PathVariable Integer id) {
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            return employee.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/jpa")
    public ResponseEntity<Employee> createEmployeeJPA(@RequestBody Employee employee) {
        try {
            // Set ID to null to ensure it's auto-generated
            employee.setId(null);
            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/jpa/{id}")
    public ResponseEntity<Employee> updateEmployeeJPA(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            Optional<Employee> existingEmployee = employeeRepository.findById(id);
            if (existingEmployee.isPresent()) {
                employee.setId(id); // Set the ID from path variable
                Employee updatedEmployee = employeeRepository.save(employee);
                return ResponseEntity.ok(updatedEmployee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/jpa/{id}")
    public ResponseEntity<Void> deleteEmployeeJPA(@PathVariable Integer id) {
        try {
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                employeeRepository.deleteById(id); // Pass ID, not Employee object
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Hibernate endpoints
    @GetMapping("/hibernate")
    public ResponseEntity<List<Employee>> getAllEmployeesHibernate() {
        try {
            List<Employee> employees = hibernateEmployeeService.getAllEmployees();
            if (employees != null) {
                return ResponseEntity.ok(employees);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/hibernate/{id}")
    public ResponseEntity<Employee> getEmployeeByIdHibernate(@PathVariable Integer id) {
        try {
            Employee employee = hibernateEmployeeService.getEmployeeById(id);
            if (employee != null) {
                return ResponseEntity.ok(employee);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/hibernate")
    public ResponseEntity<Employee> createEmployeeHibernate(@RequestBody Employee employee) {
        try {
            // Set ID to null to ensure it's auto-generated
            employee.setId(null);
            Employee savedEmployee = hibernateEmployeeService.addEmployeeWithHibernate(employee);
            if (savedEmployee != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/hibernate/{id}")
    public ResponseEntity<Employee> updateEmployeeHibernate(@PathVariable Integer id, @RequestBody Employee employee) {
        try {
            // Check if employee exists
            Employee existingEmployee = hibernateEmployeeService.getEmployeeById(id);
            if (existingEmployee != null) {
                employee.setId(id); // Set the ID from path variable
                Employee updatedEmployee = hibernateEmployeeService.updateEmployee(employee);
                if (updatedEmployee != null) {
                    return ResponseEntity.ok(updatedEmployee);
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/hibernate/{id}")
    public ResponseEntity<Void> deleteEmployeeHibernate(@PathVariable Integer id) {
        try {
            boolean deleted = hibernateEmployeeService.deleteEmployee(id); // Pass ID, not Employee object
            if (deleted) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Additional query endpoints to demonstrate differences
    @GetMapping("/jpa/by-email")
    public ResponseEntity<Employee> getEmployeeByEmailJPA(@RequestParam String email) {
        try {
            Optional<Employee> employee = employeeRepository.findByEmail(email);
            return employee.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/jpa/by-department")
    public ResponseEntity<List<Employee>> getEmployeesByDepartmentJPA(@RequestParam String department) {
        try {
            List<Employee> employees = employeeRepository.findByDepartment(department);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/jpa/by-salary-range")
    public ResponseEntity<List<Employee>> getEmployeesBySalaryRangeJPA(
            @RequestParam Double minSalary,
            @RequestParam Double maxSalary) {
        try {
            List<Employee> employees = employeeRepository.findEmployeesBySalaryRange(minSalary, maxSalary);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/jpa/count-by-department")
    public ResponseEntity<Long> countEmployeesByDepartmentJPA(@RequestParam String department) {
        try {
            long count = employeeRepository.countByDepartment(department);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Employee Management API is running");
    }
}