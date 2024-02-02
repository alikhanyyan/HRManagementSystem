package org.example.dao;

import org.example.entities.Department;
import org.example.entities.Employee;
import org.example.entities.Manager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DepartmentDAO {
    public static void createDepartment(Session session, String name, String location) {
        Transaction transaction = session.beginTransaction();

        Department department = new Department(name, location);

        session.persist(department);
        session.flush();

        transaction.commit();

        System.out.println("Department successfully created");
    }
    public static void updateDepartment(Session session, long departmentId, String newName, String newLocation) {
        Transaction transaction = session.beginTransaction();

        Department department = session.get(Department.class, departmentId);

        if (department != null) {
            if (!newName.equals("-")) {
                department.setDepartmentName(newName);
            }
            if (!newLocation.equals("-")) {
                department.setLocation(newLocation);
            }

            session.persist(department);
            session.flush();
            System.out.println("Department successfully updated");
        } else {
            System.out.println("Department with ID " + departmentId + " not found.");
        }

        transaction.commit();
    }
    public static void deleteDepartment(Session session, long departmentId) {
        Transaction transaction = session.beginTransaction();

        Department department = session.get(Department.class, departmentId);

        if (department != null) {
            session.delete(department);
            session.flush();
            System.out.println("Department successfully deleted");
        } else {
            System.out.println("Department not found with ID: " + departmentId);
        }

        transaction.commit();
    }
    public static void assignEmployeeToDepartment(Session session, long employeeId, long departmentId) {
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeId);
        Department department = session.get(Department.class, departmentId);

        if (employee != null && department != null) {
            employee.setDepartment(department);
            session.persist(employee);
            session.flush();

            System.out.println("Employee assigned to department successfully");
        } else {
            System.out.println("Employee or department not found with provided IDs");
        }

        transaction.commit();
    }
    public static void reassignEmployeeToDepartment(Session session, long employeeId, long newDepartmentId) {
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeId);
        Department department = session.get(Department.class, newDepartmentId);

        if (employee != null && department != null) {
            if (employee.getDepartment() != null) {
                employee.getDepartment().getEmployees().remove(employee);
            }

            employee.setDepartment(department);
            department.getEmployees().add(employee);

            session.persist(employee);
            session.persist(department);
            session.flush();

            System.out.println("Employee reassigned to a new department successfully");
        } else {
            System.out.println("Employee or new department not found with provided IDs");
        }

        transaction.commit();
    }

    public static void assignManagerToDepartment(Session session, long managerId, long departmentId) {
        Transaction transaction = session.beginTransaction();

        Manager manager = session.get(Manager.class, managerId);
        Department department = session.get(Department.class, departmentId);

        if (manager != null && department != null) {
            department.setDepartmentHead(manager);
            manager.setManagedDepartment(department);

            session.persist(manager);
            session.persist(department);
            session.flush();

            System.out.println("Manager assigned to department successfully");
        } else {
            System.out.println("Manager or department not found with provided IDs");
        }

        transaction.commit();
    }
    public static void reassignManagerToDepartment(Session session, long managerId, long newDepartmentId) {
        Transaction transaction = session.beginTransaction();

        Manager manager = session.get(Manager.class, managerId);
        Department newDepartment = session.get(Department.class, newDepartmentId);

        if (manager != null && newDepartment != null) {
            if (manager.getManagedDepartment() != null) {
                manager.getManagedDepartment().setDepartmentHead(null);
            }

            manager.setManagedDepartment(newDepartment);
            newDepartment.setDepartmentHead(manager);

            session.persist(manager);
            session.persist(newDepartment);
            session.flush();

            System.out.println("Manager reassigned to a new department successfully");
        } else {
            System.out.println("Manager or new department not found with provided IDs");
        }

        transaction.commit();
    }
}