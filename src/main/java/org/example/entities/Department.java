package org.example.entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departments")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "location")
    private String location;

    @OneToOne
    @JoinColumn(name = "head_id")
    private Manager departmentHead;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    public Department() {
    }
    public Department(String departmentName, String location) {
        this.departmentName = departmentName;
        this.location = location;
    }
    public Department(String departmentName, String location, Manager departmentHead, Set<Employee> employees) {
        this.departmentName = departmentName;
        this.location = location;
        this.departmentHead = departmentHead;
        this.employees = employees;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public Manager getDepartmentHead() {
        return departmentHead;
    }
    public void setDepartmentHead(Manager departmentHead) {
        this.departmentHead = departmentHead;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}