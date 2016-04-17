package com.kyrosoft.ims.service.impl;

import com.kyrosoft.ims.model.Department;
import com.kyrosoft.ims.model.Employee;
import com.kyrosoft.ims.model.dto.*;
import com.kyrosoft.ims.service.BaseSearchService;
import com.kyrosoft.ims.service.DepartmentService;
import com.kyrosoft.ims.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 4/15/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:TestContext.xml")
public class EmployeeServiceTest {

    private String strTest = "test";

    private String strTestUpdated = "updated";

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    BaseSearchService baseSearchService;

    @Test
    public void employeeSaveTest() throws Exception{

        Employee employee = new Employee();
        employee.setAddress(strTest);
        employee.setFirstname(strTest);
        employee.setLastname(strTest);
        employee.setMobile(strTest);
        employee.setNik(strTest);
        employee.setUsername(strTest);

        employeeService.save(employee);

    }

    @Test
    public void employeeUpdateTest() throws Exception {
        Department department = new Department();
        department.setName(strTest);
        departmentService.save(department);

        Employee employee = new Employee();
        employee.setAddress(strTest);
        employee.setFirstname(strTest);
        employee.setLastname(strTest);
        employee.setMobile(strTest);
        employee.setNik(strTest);
        employee.setUsername(strTest);

        employeeService.save(employee);

        Employee update = new Employee();
        update.setId(employee.getId());
        update.setAddress(strTestUpdated);
        update.setFirstname(strTestUpdated);
        update.setLastname(strTestUpdated);
        update.setMobile(strTestUpdated);
        update.setNik(strTestUpdated);
        update.setUsername(strTestUpdated);
        update.setDepartment(department);

        employeeService.update(update);
    }

    /*
    @Test
    public void employeeSearchTest() throws Exception{

        Employee employee = new Employee();
        employee.setAddress(strTest);
        employee.setFirstname(strTest);
        employee.setLastname(strTest);
        employee.setMobile(strTest);
        employee.setNik(strTest);
        employee.setUsername(strTest);

        employeeService.save(employee);

        EmployeeSearchCriteria criteria = new EmployeeSearchCriteria();
        criteria.setUsername_eq(strTest);

        SearchResult<Employee> searchResult = baseSearchService.search(criteria);
        assertEquals(1,searchResult.getTotal());

    }
    */


}
