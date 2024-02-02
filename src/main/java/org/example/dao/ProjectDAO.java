package org.example.dao;

import org.example.entities.Employee;
import org.example.entities.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class ProjectDAO {
    public static void createProject(Session session, String name, LocalDate startDate, LocalDate endDate, double budget) {
        Transaction transaction = session.beginTransaction();

        Project project = new Project(name, startDate, endDate, budget);

        session.persist(project);
        session.flush();

        transaction.commit();

        System.out.println("Project successfully created");
    }
    public static void updateProject(Session session, long projectId, String newName, LocalDate newStartDate, LocalDate newEndDate, double newBudget) {
        Transaction transaction = session.beginTransaction();

        Project project = session.get(Project.class, projectId);

        if (project != null) {
            if (!newName.equals("-")) {
                project.setProjectName(newName);
            }
            if (newStartDate != null) {
                project.setStartDate(newStartDate);
            }
            if (newEndDate != null) {
                project.setStartDate(newEndDate);
            }
            if (newBudget != -1) {
                project.setBudget(newBudget);
            }

            session.persist(project);
            session.flush();
            System.out.println("Project successfully updated");
        } else {
            System.out.println("Project not found with ID: " + projectId);
        }

        transaction.commit();
    }
    public static void deleteProject(Session session, long projectId) {
        Transaction transaction = session.beginTransaction();

        Project project = session.get(Project.class, projectId);

        if (project != null) {
            session.delete(project);
            session.flush();
            System.out.println("Project successfully deleted");
        } else {
            System.out.println("Project not found with ID: " + projectId);
        }

        transaction.commit();
    }

    public static void assignEmployeeToProject(Session session, long employeeId, long projectId) {
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeId);
        Project project = session.get(Project.class, projectId);

        if (employee != null && project != null) {
            employee.getProjects().add(project);
            project.getEmployees().add(employee);

            session.persist(employee);
            session.persist(project);
            session.flush();
            System.out.println("Employee assigned to project successfully");
        } else {
            System.out.println("Employee or project not found with provided IDs");
        }

        transaction.commit();
    }
    public static void reassignEmployeeToProject(Session session, long employeeId, long newProjectId) {
        Transaction transaction = session.beginTransaction();

        Employee employee = session.get(Employee.class, employeeId);
        Project newProject = session.get(Project.class, newProjectId);

        if (employee != null && newProject != null) {
            for (Project existingProject : employee.getProjects()) {
                existingProject.getEmployees().remove(employee);
            }
            employee.getProjects().clear();

            employee.getProjects().add(newProject);
            newProject.getEmployees().add(employee);

            session.persist(employee);
            session.persist(newProject);
            session.flush();

            System.out.println("Employee reassigned to a new project successfully");
        } else {
            System.out.println("Employee or new project not found with provided IDs");
        }

        transaction.commit();
    }
}