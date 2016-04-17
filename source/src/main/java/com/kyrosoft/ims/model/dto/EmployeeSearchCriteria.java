package com.kyrosoft.ims.model.dto;

import com.kyrosoft.ims.model.*;
import java.util.*;

public class EmployeeSearchCriteria extends BaseSearchParameters {

    private String nik_eq;
    private String nik_like;
    private String username_eq;
    private String username_like;
    private String firstname_eq;
    private String firstname_like;
    private String lastname_eq;
    private String lastname_like;
    private String mobile_eq;
    private String mobile_like;
    private String address_eq;
    private String address_like;
    private Integer yearStart_eq;
    private Integer yearStart_ge;
    private Integer yearStart_gt;
    private Integer yearStart_le;
    private Integer yearStart_lt;
    private Double salary_eq;
    private Double salary_ge;
    private Double salary_gt;
    private Double salary_le;
    private Double salary_lt;
    private Boolean isAdmin_eq;
    private Date lastLogin_eq;
    private Date lastLogin_ge;
    private Date lastLogin_gt;
    private Date lastLogin_le;
    private Date lastLogin_lt;
    private EmployeeLevel employeeLevel_eq;
    private Long id_eq;
    private Long id_ge;
    private Long id_gt;
    private Long id_le;
    private Long id_lt;

    public EmployeeSearchCriteria() {}

    public String getNik_eq() {
        return nik_eq;
    }

    public void setNik_eq(String nik_eq) {
        this.nik_eq = nik_eq;
    }
    public String getNik_like() {
        return nik_like;
    }

    public void setNik_like(String nik_like) {
        this.nik_like = nik_like;
    }
    public String getUsername_eq() {
        return username_eq;
    }

    public void setUsername_eq(String username_eq) {
        this.username_eq = username_eq;
    }
    public String getUsername_like() {
        return username_like;
    }

    public void setUsername_like(String username_like) {
        this.username_like = username_like;
    }
    public String getFirstname_eq() {
        return firstname_eq;
    }

    public void setFirstname_eq(String firstname_eq) {
        this.firstname_eq = firstname_eq;
    }
    public String getFirstname_like() {
        return firstname_like;
    }

    public void setFirstname_like(String firstname_like) {
        this.firstname_like = firstname_like;
    }
    public String getLastname_eq() {
        return lastname_eq;
    }

    public void setLastname_eq(String lastname_eq) {
        this.lastname_eq = lastname_eq;
    }
    public String getLastname_like() {
        return lastname_like;
    }

    public void setLastname_like(String lastname_like) {
        this.lastname_like = lastname_like;
    }
    public String getMobile_eq() {
        return mobile_eq;
    }

    public void setMobile_eq(String mobile_eq) {
        this.mobile_eq = mobile_eq;
    }
    public String getMobile_like() {
        return mobile_like;
    }

    public void setMobile_like(String mobile_like) {
        this.mobile_like = mobile_like;
    }
    public String getAddress_eq() {
        return address_eq;
    }

    public void setAddress_eq(String address_eq) {
        this.address_eq = address_eq;
    }
    public String getAddress_like() {
        return address_like;
    }

    public void setAddress_like(String address_like) {
        this.address_like = address_like;
    }
    public Integer getYearStart_eq() {
        return yearStart_eq;
    }

    public void setYearStart_eq(Integer yearStart_eq) {
        this.yearStart_eq = yearStart_eq;
    }
    public Integer getYearStart_ge() {
        return yearStart_ge;
    }

    public void setYearStart_ge(Integer yearStart_ge) {
        this.yearStart_ge = yearStart_ge;
    }
    public Integer getYearStart_gt() {
        return yearStart_gt;
    }

    public void setYearStart_gt(Integer yearStart_gt) {
        this.yearStart_gt = yearStart_gt;
    }
    public Integer getYearStart_le() {
        return yearStart_le;
    }

    public void setYearStart_le(Integer yearStart_le) {
        this.yearStart_le = yearStart_le;
    }
    public Integer getYearStart_lt() {
        return yearStart_lt;
    }

    public void setYearStart_lt(Integer yearStart_lt) {
        this.yearStart_lt = yearStart_lt;
    }
    public Double getSalary_eq() {
        return salary_eq;
    }

    public void setSalary_eq(Double salary_eq) {
        this.salary_eq = salary_eq;
    }
    public Double getSalary_ge() {
        return salary_ge;
    }

    public void setSalary_ge(Double salary_ge) {
        this.salary_ge = salary_ge;
    }
    public Double getSalary_gt() {
        return salary_gt;
    }

    public void setSalary_gt(Double salary_gt) {
        this.salary_gt = salary_gt;
    }
    public Double getSalary_le() {
        return salary_le;
    }

    public void setSalary_le(Double salary_le) {
        this.salary_le = salary_le;
    }
    public Double getSalary_lt() {
        return salary_lt;
    }

    public void setSalary_lt(Double salary_lt) {
        this.salary_lt = salary_lt;
    }
    public Boolean getIsAdmin_eq() {
        return isAdmin_eq;
    }

    public void setIsAdmin_eq(Boolean isAdmin_eq) {
        this.isAdmin_eq = isAdmin_eq;
    }
    public Date getLastLogin_eq() {
        return lastLogin_eq;
    }

    public void setLastLogin_eq(Date lastLogin_eq) {
        this.lastLogin_eq = lastLogin_eq;
    }
    public Date getLastLogin_ge() {
        return lastLogin_ge;
    }

    public void setLastLogin_ge(Date lastLogin_ge) {
        this.lastLogin_ge = lastLogin_ge;
    }
    public Date getLastLogin_gt() {
        return lastLogin_gt;
    }

    public void setLastLogin_gt(Date lastLogin_gt) {
        this.lastLogin_gt = lastLogin_gt;
    }
    public Date getLastLogin_le() {
        return lastLogin_le;
    }

    public void setLastLogin_le(Date lastLogin_le) {
        this.lastLogin_le = lastLogin_le;
    }
    public Date getLastLogin_lt() {
        return lastLogin_lt;
    }

    public void setLastLogin_lt(Date lastLogin_lt) {
        this.lastLogin_lt = lastLogin_lt;
    }
    public EmployeeLevel getEmployeeLevel_eq() {
        return employeeLevel_eq;
    }

    public void setEmployeeLevel_eq(EmployeeLevel employeeLevel_eq) {
        this.employeeLevel_eq = employeeLevel_eq;
    }
    public Long getId_eq() {
        return id_eq;
    }

    public void setId_eq(Long id_eq) {
        this.id_eq = id_eq;
    }
    public Long getId_ge() {
        return id_ge;
    }

    public void setId_ge(Long id_ge) {
        this.id_ge = id_ge;
    }
    public Long getId_gt() {
        return id_gt;
    }

    public void setId_gt(Long id_gt) {
        this.id_gt = id_gt;
    }
    public Long getId_le() {
        return id_le;
    }

    public void setId_le(Long id_le) {
        this.id_le = id_le;
    }
    public Long getId_lt() {
        return id_lt;
    }

    public void setId_lt(Long id_lt) {
        this.id_lt = id_lt;
    }
}