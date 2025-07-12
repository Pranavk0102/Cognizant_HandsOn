package com.example.jpahibernatedemo.service;

import com.example.jpahibernatedemo.entity.Employee;
import com.example.jpahibernatedemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // CREATE
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // READ
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // UPDATE
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // DELETE
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }

    // Custom business logic
    public List<Employee> getHighEarners(Double minSalary) {
        return employeeRepository.findBySalaryGreaterThan(minSalary);
    }

    public long getEmployeeCount() {
        return employeeRepository.count();
    }
}