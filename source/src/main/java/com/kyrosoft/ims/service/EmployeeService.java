package com.kyrosoft.ims.service;

import com.kyrosoft.ims.model.Employee;

/**
 * Created by Administrator on 4/15/2016.
 */
public interface EmployeeService {

    public Employee save(Employee employee);

    public Employee update(Employee employee) throws Exception;
}
