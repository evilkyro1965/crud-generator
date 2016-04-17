package com.kyrosoft.ims.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 4/15/2016.
 */
@Entity(name = "employee")
public class Employee extends IdentifiableEntity {

    @Basic
    private String nik;

    @ManyToOne
    @JoinColumn(name="departmentId")
    private Department department;

    @Basic
    private String username;

    @Basic
    private String firstname;

    @Basic
    private String lastname;

    @Basic
    private String mobile;

    @Basic
    private String address;

    @Basic
    private Integer yearStart;

    @Basic
    private Double salary;

    @Basic
    private Boolean isAdmin;

    @Basic
    private Date lastLogin;

    @Enumerated
    private EmployeeLevel employeeLevel;

    public Employee() {}

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getYearStart() {
        return yearStart;
    }

    public void setYearStart(Integer yearStart) {
        this.yearStart = yearStart;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public EmployeeLevel getEmployeeLevel() {
        return employeeLevel;
    }

    public void setEmployeeLevel(EmployeeLevel employeeLevel) {
        this.employeeLevel = employeeLevel;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
