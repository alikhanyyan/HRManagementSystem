package org.example.entities;

import org.example.ManagementLevel;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "managers")
@DiscriminatorValue(value = "MANAGER")
public class Manager extends Employee {
    @OneToOne(mappedBy = "departmentHead")
    private Department managedDepartment;

    @Enumerated(EnumType.STRING)
    @Column(name = "management_level")
    private ManagementLevel managementLevel;

    public Manager() {
    }
    public Manager(String name, String email, String phoneNumber, LocalDate hireDate, String jobTitle, ManagementLevel managementLevel) {
        super(name, email, phoneNumber, hireDate, jobTitle);
        this.managementLevel = managementLevel;
    }

    public Department getManagedDepartment() {
        return managedDepartment;
    }
    public void setManagedDepartment(Department managedDepartment) {
        this.managedDepartment = managedDepartment;
    }

    public ManagementLevel getManagementLevel() {
        return managementLevel;
    }
    public void setManagementLevel(ManagementLevel managementLevel) {
        this.managementLevel = managementLevel;
    }
}