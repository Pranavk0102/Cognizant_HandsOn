package com.example.jpahibernatedemo.service;

import com.example.jpahibernatedemo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeHibernateService {

    @Autowired
    private SessionFactory sessionFactory;

    // CREATE - Traditional Hibernate approach
    public Integer addEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            employeeID = (Integer) session.save(employee);
            tx.commit();
            System.out.println("Employee created with ID: " + employeeID);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    // READ - Get employee by ID
    public Employee getEmployeeById(Integer id) {
        Session session = sessionFactory.openSession();
        Employee employee = null;

        try {
            employee = session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

    // READ - Get all employees
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        List<Employee> employees = null;

        try {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            employees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    // UPDATE
    public void updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
            System.out.println("Employee updated successfully");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // DELETE
    public void deleteEmployee(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
                System.out.println("Employee deleted successfully");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Custom HQL query
    public List<Employee> getEmployeesByDepartment(String department) {
        Session session = sessionFactory.openSession();
        List<Employee> employees = null;

        try {
            Query<Employee> query = session.createQuery(
                    "FROM Employee e WHERE e.department = :dept", Employee.class);
            query.setParameter("dept", department);
            employees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }
}