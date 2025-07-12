package com.example.demo.service;

import com.example.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class HibernateEmployeeService {

    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        try {
            // Create SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Employee.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Failed to create SessionFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public Employee addEmployeeWithHibernate(Employee employee) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Save the employee
            session.persist(employee);

            // Commit the transaction
            transaction.commit();

            System.out.println("Employee saved with Hibernate: " + employee);
            return employee;

        } catch (Exception e) {
            // Rollback transaction if it exists and is active
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                    System.out.println("Transaction rolled back due to error: " + e.getMessage());
                } catch (Exception rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }

            System.err.println("Error saving employee with Hibernate: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to save employee", e);

        } finally {
            // Close session if it exists
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception closeEx) {
                    System.err.println("Error closing session: " + closeEx.getMessage());
                }
            }
        }
    }

    public List<Employee> getAllEmployeesWithHibernate() {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Create query to get all employees
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();

            transaction.commit();
            return employees;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }

            System.err.println("Error retrieving employees with Hibernate: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve employees", e);

        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception closeEx) {
                    System.err.println("Error closing session: " + closeEx.getMessage());
                }
            }
        }
    }

    public Employee findEmployeeByIdWithHibernate(Long id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);

            transaction.commit();
            return employee;

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }

            System.err.println("Error finding employee with Hibernate: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to find employee", e);

        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception closeEx) {
                    System.err.println("Error closing session: " + closeEx.getMessage());
                }
            }
        }
    }

    public void updateEmployeeWithHibernate(Employee employee) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.merge(employee);

            transaction.commit();
            System.out.println("Employee updated with Hibernate: " + employee);

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }

            System.err.println("Error updating employee with Hibernate: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to update employee", e);

        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception closeEx) {
                    System.err.println("Error closing session: " + closeEx.getMessage());
                }
            }
        }
    }

    public void deleteEmployeeWithHibernate(Long id) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.remove(employee);
                System.out.println("Employee deleted with Hibernate: " + employee);
            } else {
                System.out.println("Employee not found with ID: " + id);
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (Exception rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }

            System.err.println("Error deleting employee with Hibernate: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to delete employee", e);

        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (Exception closeEx) {
                    System.err.println("Error closing session: " + closeEx.getMessage());
                }
            }
        }
    }
}