package org.example.dao;

import org.example.entities.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class EmployeeDAO {
    public static void createEmployee(Session session, String name, String email, String phoneNumber, LocalDate hireDate, String jobTitle) {
        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee(name, email, phoneNumber, hireDate, jobTitle);
        session.persist(employee);
        session.flush();

        transaction.commit();

        System.out.println("Employee created successfully");
    }
    public static void updateEmployee(Session session, long employeeId, String newName, String newEmail, String newPhoneNumber,
                                      LocalDate newHireDate, String newJobTitle) {
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeId);

        if (employee != null) {
            if (!newName.equals("-")) {
                employee.setName(newName);
            }
            if (!newEmail.equals("-")) {
                employee.setEmail(newEmail);
            }
            if (!newPhoneNumber.equals("-")) {
                employee.setPhoneNumber(newPhoneNumber);
            }
            if (newHireDate != null) {
                employee.setHireDate(newHireDate);
            }
            if (!newJobTitle.equals("-")) {
                employee.setJobTitle(newJobTitle);
            }

            session.persist(employee);
            session.flush();

            System.out.println("Employee updated successfully");
        } else {
            System.out.println("Employee not found with ID: " + employeeId);
        }

        transaction.commit();
    }
    public static void deleteEmployee(Session session, long employeeId) {
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeId);

        if (employee != null) {
            session.delete(employee);
            session.flush();

            System.out.println("Employee deleted successfully");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
        }

        transaction.commit();
    }
}