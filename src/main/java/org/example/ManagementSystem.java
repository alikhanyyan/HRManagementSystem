package org.example;

import org.example.dao.DepartmentDAO;
import org.example.dao.EmployeeDAO;
import org.example.dao.ManagerDAO;
import org.example.dao.ProjectDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static org.example.MenuCodes.*;

public class ManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfig.getSessionFactory();
        Session session = sessionFactory.openSession();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println(CREATE_DEP + ". Create department");
            System.out.println(UPDATE_DEP + ". Update department");
            System.out.println(DELETE_DEP + ". Delete department");
            System.out.println(ASSIGN_EMP_TO_DEP + ". Assign employee to department");
            System.out.println(REASSIGN_EMP_TO_DEP + ". Reassign employee to department");
            System.out.println(CREATE_PROJECT + ". Create project");
            System.out.println(UPDATE_PROJECT + ". Update project");
            System.out.println(DELETE_PROJECT + ". Delete project");
            System.out.println(ASSIGN_EMP_TO_PROJECT + ". Assign employee to project");
            System.out.println(REASSIGN_EMP_TO_PROJECT + ". Reassign employee to project");
            System.out.println(ASSIGN_MANAGER_TO_DEP + ". Assign manager to department");
            System.out.println(REASSIGN_MANAGER_TO_DEP + ". Reassign manager to department");
            System.out.println(VIEW_MANAGER_SUBORDINATES + ". View manager subordinates");
            System.out.println(CHANGE_MANAGER_MANAGE_LEVEL + ". Change manager management level");
            System.out.println("Optional");
            System.out.println(CREATE_EMP + ". Create employee");
            System.out.println(UPDATE_EMP + ". Update employee");
            System.out.println(DELETE_EMP + ". Delete employee");
            System.out.println(CREATE_MANAGER + ". Create manager");
            System.out.println(UPDATE_MANAGER + ". Update manager");
            System.out.println(DELETE_MANAGER + ". Delete manager");
            System.out.println(EXIT + ". Exit");
            System.out.println();

            String choice = scanner.nextLine();

            switch (getMenuCodeFromValue(choice)) {
                case CREATE_DEP -> createDepartment(session);
                case UPDATE_DEP -> updateDepartment(session);
                case DELETE_DEP -> deleteDepartment(session);
                case ASSIGN_EMP_TO_DEP -> assignEmpToDepartment(session);
                case REASSIGN_EMP_TO_DEP -> reassignEmpToDepartment(session);
                case CREATE_PROJECT -> createProject(session);
                case UPDATE_PROJECT -> updateProject(session);
                case DELETE_PROJECT -> deleteProject(session);
                case ASSIGN_EMP_TO_PROJECT -> assignEmpToProject(session);
                case REASSIGN_EMP_TO_PROJECT -> reassignEmpToProject(session);
                case ASSIGN_MANAGER_TO_DEP -> assignManagerToDep(session);
                case REASSIGN_MANAGER_TO_DEP -> reassignManagerToDep(session);
                case VIEW_MANAGER_SUBORDINATES -> viewManagerSubordinates(session);
                case CHANGE_MANAGER_MANAGE_LEVEL -> changeManagementLevel(session);
                case CREATE_EMP -> createEmployee(session);
                case UPDATE_EMP -> updateEmployee(session);
                case DELETE_EMP -> deleteEmployee(session);
                case CREATE_MANAGER -> createManager(session);
                case UPDATE_MANAGER -> updateManager(session);
                case DELETE_MANAGER -> deleteManager(session);
                case EXIT -> {
                    System.out.println("Exiting.");
                    return;
                }
                case null -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    // Create Department
    private static void createDepartment(Session session) {
        System.out.print("Enter department name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter department location: ");
        String location = scanner.nextLine().trim();

        DepartmentDAO.createDepartment(session, name, location);
    }
    // Update Department Details
    private static void updateDepartment(Session session) {
        long departmentId;
        String newName;
        String newLocation;
        System.out.print("Enter department ID: ");
        try {
            departmentId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Enter new name or - if you don't want to change it: ");
        newName = scanner.nextLine().trim();
        System.out.print("Enter new location or - if you don't want to change it: ");
        newLocation = scanner.nextLine().trim();

        DepartmentDAO.updateDepartment(session, departmentId, newName, newLocation);
    }
    // Delete Department
    private static void deleteDepartment(Session session) {
        long departmentId;

        System.out.print("Enter department ID: ");
        try {
            departmentId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        DepartmentDAO.deleteDepartment(session, departmentId);
    }

    // Assign Employee to Department
    private static void assignEmpToDepartment(Session session) {
        long employeeId;
        long departmentId;
        System.out.print("Enter employee ID: ");
        try {
            employeeId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter department ID: ");
            departmentId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        DepartmentDAO.assignEmployeeToDepartment(session, employeeId, departmentId);
    }
    // Reassign Employee to New Department
    private static void reassignEmpToDepartment(Session session) {
        long employeeId;
        long newDepartmentId;
        System.out.print("Enter employee ID: ");
        try {
            employeeId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter department ID: ");
            newDepartmentId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        DepartmentDAO.reassignEmployeeToDepartment(session, employeeId, newDepartmentId);
    }

    // Create Project
    private static void createProject(Session session) {
        System.out.print("Enter project name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter project start date (format: yyyy-MM-dd): ");
        LocalDate startDate;
        LocalDate endDate;
        double budget;
        try {
            startDate = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Enter project end date (format: yyyy-MM-dd): ");
            endDate = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Enter project budget: ");
            budget = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println(e.getMessage());
            return;
        }

        ProjectDAO.createProject(session, name, startDate, endDate, budget);
    }
    // Update Project Details
    private static void updateProject(Session session) {
        System.out.print("Enter project ID: ");
        long projectId;
        try {
            projectId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Enter new name or - if you don't want to change it: ");
        String newName = scanner.nextLine().trim();
        System.out.print("Enter new start date (format: yyyy-MM-dd) or - if you don't want to change it: ");
        LocalDate newStartDate = null;
        LocalDate newEndDate = null;
        double newBudget = -1;
        try {
            String strDate = scanner.nextLine().trim();
            newStartDate = strDate.equals("-") ? null : LocalDate.parse(strDate);
            System.out.print("Enter project end date (format: yyyy-MM-dd): ");
            strDate = scanner.nextLine().trim();
            newEndDate = strDate.equals("-") ? null : LocalDate.parse(strDate);
            System.out.print("Enter project budget or -1 if you don't want to change it: ");
            newBudget = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }

        ProjectDAO.updateProject(session, projectId, newName, newStartDate, newEndDate, newBudget);
    }
    // Delete Project
    private static void deleteProject(Session session) {
        long projectId;
        System.out.print("Enter project ID: ");
        try {
            projectId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        ProjectDAO.deleteProject(session, projectId);
    }

    // Assign Employee to Project
    private static void assignEmpToProject(Session session) {
        long employeeId;
        long projectId;
        System.out.print("Enter employee ID: ");
        try {
            employeeId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter project ID: ");
            projectId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        ProjectDAO.assignEmployeeToProject(session, employeeId, projectId);
    }
    // Reassign Employee to New Project
    private static void reassignEmpToProject(Session session) {
        long employeeId;
        long newProjectId;
        System.out.print("Enter employee ID: ");
        try {
            employeeId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter new project ID: ");
            newProjectId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        ProjectDAO.reassignEmployeeToProject(session, employeeId, newProjectId);
    }

    // Assign Manager to Department
    private static void assignManagerToDep(Session session) {
        long managerId;
        long departmentId;
        System.out.print("Enter manager ID: ");
        try {
            managerId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter department ID: ");
            departmentId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        DepartmentDAO.assignManagerToDepartment(session, managerId, departmentId);
    }
    // Reassign Manager to New Department
    private static void reassignManagerToDep(Session session) {
        long managerId;
        long newDepartmentId;
        System.out.print("Enter manager ID: ");
        try {
            managerId = Long.parseLong(scanner.nextLine().trim());
            System.out.print("Enter new department ID: ");
            newDepartmentId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        DepartmentDAO.reassignManagerToDepartment(session, managerId, newDepartmentId);
    }

    // Print Manager Subordinates
    private static void viewManagerSubordinates(Session session) {
        long managerId;
        System.out.print("Enter manager ID: ");
        try {
            managerId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        ManagerDAO.showManagerSubordinates(session, managerId);
    }

    // Change Manager Management Level
    private static void changeManagementLevel(Session session) {
        long managerId;
        ManagementLevel newLevel;
        System.out.print("Enter manager ID: ");
        try {
            managerId = Long.parseLong(scanner.nextLine().trim());

            System.out.print("Enter new management level (TOP_LEVEL, MID_LEVEL, FIRST_LINE): ");
            String newLevelString = scanner.nextLine().trim().toUpperCase();
            newLevel = ManagementLevel.valueOf(newLevelString);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid management level");
            return;
        }

        ManagerDAO.changeManagementLevel(session, managerId, newLevel);
    }

    //---------------------------------------------------OPTIONAL---------------------------------------------------
    // Create Employee
    private static void createEmployee(Session session) {
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter employee email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter employee phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Enter employee hire date (yyyy-MM-dd): ");
        LocalDate hireDate;
        try {
            hireDate = LocalDate.parse(scanner.nextLine().trim());
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Enter employee job title: ");
        String jobTitle = scanner.nextLine().trim();

        EmployeeDAO.createEmployee(session, name, email, phoneNumber, hireDate, jobTitle);
    }
    // Update Employee Details
    private static void updateEmployee(Session session) {
        long employeeId;
        System.out.print("Enter employee ID: ");
        try {
            employeeId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Enter new name or - if you don't want to change it: ");
        String newName = scanner.nextLine().trim();
        System.out.print("Enter new email or - if you don't want to change it: ");
        String newEmail = scanner.nextLine().trim();
        System.out.print("Enter new phone number or - if you don't want to change it: ");
        String newPhoneNumber = scanner.nextLine().trim();
        System.out.print("Enter new hire date (yyyy-MM-dd) or - if you don't want to change it: ");
        String strHireDate = scanner.nextLine().trim();
        LocalDate newHireDate;
        try {
            newHireDate = strHireDate.equals("-") ? null : LocalDate.parse(strHireDate);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Enter new job title or - if you don't want to change it: ");
        String newJobTitle = scanner.nextLine().trim();

        EmployeeDAO.updateEmployee(session, employeeId, newName, newEmail, newPhoneNumber, newHireDate, newJobTitle);
    }
    // Delete Employee
    private static void deleteEmployee(Session session) {
        long employeeId;
        System.out.print("Enter employee ID to delete: ");
        try {
            employeeId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        EmployeeDAO.deleteEmployee(session, employeeId);
    }

    // Create Manager
    private static void createManager(Session session) {
        System.out.print("Enter manager name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter manager email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter manager phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.print("Enter manager job title: ");
        String jobTitle = scanner.nextLine().trim();
        System.out.print("Enter manager hire date (yyyy-MM-dd): ");
        LocalDate hireDate;
        ManagementLevel managementLevel;
        try {
            hireDate = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Enter management level (TOP_LEVEL, MID_LEVEL, FIRST_LINE): ");
            managementLevel = ManagementLevel.valueOf(scanner.nextLine().trim().toUpperCase());
        } catch (IllegalArgumentException | DateTimeParseException  e) {
            System.out.println(e.getMessage());
            return;
        }

        ManagerDAO.createManager(session, name, email, phoneNumber, hireDate, jobTitle, managementLevel);
    }
    // Update Manager Detail
    private static void updateManager(Session session) {
        long managerId;
        System.out.print("Enter manager ID: ");
        try {
            managerId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.print("Enter new name or - if you don't want to change it: ");
        String newName = scanner.nextLine().trim();
        System.out.print("Enter new email or - if you don't want to change it: ");
        String newEmail = scanner.nextLine().trim();
        System.out.print("Enter new phone number or - if you don't want to change it: ");
        String newPhoneNumber = scanner.nextLine().trim();
        System.out.print("Enter new job title or - if you don't want to change it: ");
        String newJobTitle = scanner.nextLine().trim();
        System.out.print("Enter new hire date (yyyy-MM-dd) or - if you don't want to change it: ");
        LocalDate newHireDate;
        ManagementLevel newManagementLevel;
        try {
            String strNewHireDate = scanner.nextLine().trim();
            newHireDate = strNewHireDate.equals("-") ? null : LocalDate.parse(strNewHireDate);
            System.out.print("Enter new management level (TOP_LEVEL, MID_LEVEL, FIRST_LINE) or - if you don't want to change it: ");
            String strManagementLevel = scanner.nextLine().trim().toUpperCase();
            newManagementLevel = strManagementLevel.equals("-") ? null : ManagementLevel.valueOf(strManagementLevel);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            System.out.println(e.getMessage());
            return;
        }

        ManagerDAO.updateManager(session, managerId, newName, newEmail, newPhoneNumber, newHireDate, newJobTitle, newManagementLevel);
    }
    // Delete Manager
    private static void deleteManager(Session session) {
        long managerId;
        System.out.print("Enter manager ID to delete: ");
        try {
            managerId = Long.parseLong(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
        }

        ManagerDAO.deleteManager(session, managerId);
    }
    //--------------------------------------------------------------------------------------------------------------


    // Gets Menu Code From Value
    private static MenuCodes getMenuCodeFromValue(String value) {
        value = value.trim();
        for (MenuCodes menuCode : MenuCodes.values()) {
            if (menuCode.toString().equals(value)) {
                return menuCode;
            }
        }
        return null;
    }
}