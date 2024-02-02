package org.example.dao;

import org.example.ManagementLevel;
import org.example.entities.Department;
import org.example.entities.Employee;
import org.example.entities.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class ManagerDAO {
    public static void createManager(Session session, String name, String email, String phoneNumber, LocalDate hireDate,
                                     String jobTitle, ManagementLevel managementLevel) {
        Transaction transaction = session.beginTransaction();

        Manager manager = new Manager(name, email, phoneNumber, hireDate, jobTitle, managementLevel);
        session.persist(manager);
        session.flush();

        transaction.commit();

        System.out.println("Manager created successfully");
    }
    public static void updateManager(Session session, long managerId, String newName, String newEmail, String newPhoneNumber,
                                     LocalDate newHireDate, String newJobTitle, ManagementLevel newManagementLevel) {
        Transaction transaction = session.beginTransaction();

        Manager manager = session.get(Manager.class, managerId);

        if (manager != null) {
            if (!newName.equals("-")) {
                manager.setName(newName);
            }
            if (!newEmail.equals("-")) {
                manager.setEmail(newEmail);
            }
            if (!newPhoneNumber.equals("-")) {
                manager.setPhoneNumber(newPhoneNumber);
            }
            if (!newJobTitle.equals("-")) {
                manager.setJobTitle(newJobTitle);
            }
            if (newHireDate != null) {
                manager.setHireDate(newHireDate);
            }
            if (newManagementLevel != null) {
                manager.setManagementLevel(newManagementLevel);
            }

            session.persist(manager);
            session.flush();

            System.out.println("Manager updated successfully");
        } else {
            System.out.println("Manager not found with ID: " + managerId);
        }

        transaction.commit();
    }
    public static void deleteManager(Session session, long managerId) {
        Transaction transaction = session.beginTransaction();

        Manager manager = session.get(Manager.class, managerId);

        if (manager != null) {
            session.delete(manager);
            session.flush();

            System.out.println("Manager deleted successfully");
        } else {
            System.out.println("Manager not found with ID: " + managerId);
        }

        transaction.commit();
    }

    public static void showManagerSubordinates(Session session, long managerId) {
        Transaction transaction = session.beginTransaction();

        Manager manager = session.get(Manager.class, managerId);

        if (manager != null) {
            Department department = manager.getManagedDepartment();

            if (department != null) {
                System.out.println("Subordinates for Manager " + manager.getName() + ":");
                for (Employee subordinate : department.getEmployees()){
                    System.out.println(subordinate.getName());
                }
            } else {
                System.out.println("Manager isn't assigned to any department");
            }
        } else {
            System.out.println("Manager not found with ID: " + managerId);
        }

        transaction.commit();
    }

    public static void changeManagementLevel(Session session, long managerId, ManagementLevel newLevel) {
        Transaction transaction = session.beginTransaction();

        Manager manager = session.get(Manager.class, managerId);

        if (manager != null) {
            manager.setManagementLevel(newLevel);
            System.out.println("Management level changed successfully to " + newLevel);
        } else {
            System.out.println("Manager not found with ID: " + managerId);
        }

        transaction.commit();
    }
}