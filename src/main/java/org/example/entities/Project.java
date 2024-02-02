package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "budget")
    private Double budget;

    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @JoinColumn(name = "employee")
    private Set<Employee> employees = new HashSet<>();

    public Project() {
    }
    public Project(String projectName, LocalDate startDate, LocalDate endDate, Double budget) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }
    public Project(String projectName, LocalDate startDate, LocalDate endDate, Double budget, Set<Employee> employees) {
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.employees = employees;
    }

    public Long getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getBudget() {
        return budget;
    }
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Set<Employee> getEmployees() {
        return Set.copyOf(employees);
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
