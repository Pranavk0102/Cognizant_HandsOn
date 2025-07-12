package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.service.HibernateEmployeeService;
import com.example.demo.service.SpringDataEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class EmployeeManagementApplication implements CommandLineRunner {

	@Autowired
	private SpringDataEmployeeService springDataEmployeeService;

	@Autowired
	private HibernateEmployeeService hibernateEmployeeService;

	@Autowired
	private JPAEmployeeService jpaEmployeeService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("=== JPA, Hibernate, and Spring Data JPA Demo ===\n");

		// Demo Spring Data JPA
		demoSpringDataJPA();

		// Demo Pure Hibernate
		demoPureHibernate();

		// Demo Pure JPA
		demoPureJPA();
	}

	private void demoSpringDataJPA() {
		System.out.println("1. SPRING DATA JPA DEMO:");

		// Create and save employee
		Employee employee = new Employee();
		employee.setFirstName("Alice");
		employee.setLastName("Johnson");
		employee.setEmail("alice.johnson@example.com");
		employee.setDepartment("Engineering");
		employee.setSalary(90000.0);
		employee.setHireDate(LocalDate.of(2023, 1, 15));

		System.out.println("=== Spring Data JPA: Adding Employee ===");
		Employee savedEmployee = springDataEmployeeService.saveEmployee(employee);
		System.out.println("Saved: " + savedEmployee);

		System.out.println("\n=== Spring Data JPA: Finding All Employees ===");
		List<Employee> employees = springDataEmployeeService.findAllEmployees();
		employees.forEach(System.out::println);

		System.out.println("\n=== Spring Data JPA: Custom Query Methods ===");
		List<Employee> engineeringEmployees = springDataEmployeeService.findByDepartment("Engineering");
		System.out.println("Engineering employees: " + engineeringEmployees.size());

		System.out.println();
	}

	private void demoPureHibernate() {
		System.out.println("2. PURE HIBERNATE DEMO:");

		// Create and save employee with Hibernate
		Employee employee = new Employee();
		employee.setFirstName("Bob");
		employee.setLastName("Smith");
		employee.setEmail("bob.smith@example.com");
		employee.setDepartment("Marketing");
		employee.setSalary(75000.0);
		employee.setHireDate(LocalDate.of(2023, 2, 20));

		System.out.println("=== Pure Hibernate: Adding Employee ===");
		Employee savedEmployee = hibernateEmployeeService.addEmployeeWithHibernate(employee);

		System.out.println("\n=== Pure Hibernate: Finding All Employees ===");
		List<Employee> employees = hibernateEmployeeService.getAllEmployeesWithHibernate();
		employees.forEach(System.out::println);

		System.out.println("\n=== Pure Hibernate: Finding Employee by ID ===");
		Employee foundEmployee = hibernateEmployeeService.findEmployeeByIdWithHibernate(savedEmployee.getId());
		System.out.println("Found employee: " + foundEmployee);

		System.out.println("\n=== Pure Hibernate: Updating Employee ===");
		foundEmployee.setSalary(80000.0);
		hibernateEmployeeService.updateEmployeeWithHibernate(foundEmployee);

		System.out.println();
	}

	private void demoPureJPA() {
		System.out.println("3. PURE JPA DEMO:");

		// Create and save employee with JPA
		Employee employee = new Employee();
		employee.setFirstName("Charlie");
		employee.setLastName("Brown");
		employee.setEmail("charlie.brown@example.com");
		employee.setDepartment("Sales");
		employee.setSalary(70000.0);
		employee.setHireDate(LocalDate.of(2023, 3, 10));

		System.out.println("=== Pure JPA: Adding Employee ===");
		Employee savedEmployee = jpaEmployeeService.addEmployeeWithJPA(employee);

		System.out.println("\n=== Pure JPA: Finding All Employees ===");
		List<Employee> employees = jpaEmployeeService.getAllEmployeesWithJPA();
		employees.forEach(System.out::println);

		System.out.println("\n=== Pure JPA: Finding Employee by ID ===");
		Employee foundEmployee = jpaEmployeeService.findEmployeeByIdWithJPA(savedEmployee.getId());
		System.out.println("Found employee: " + foundEmployee);

		System.out.println("\n=== Pure JPA: Custom JPQL Query ===");
		List<Employee> highSalaryEmployees = jpaEmployeeService.findEmployeesBySalaryGreaterThan(65000.0);
		System.out.println("High salary employees: " + highSalaryEmployees.size());

		System.out.println();
		System.out.println("=== Demo completed successfully! ===");
	}
}