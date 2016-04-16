package com.kyrosoft.ims.service.impl;

import com.kyrosoft.ims.model.CrudOperation;
import com.kyrosoft.ims.model.Employee;
import com.kyrosoft.ims.model.dto.BaseSearchParameters;
import com.kyrosoft.ims.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 4/15/2016.
 */
@Service
@Transactional
public class EmployeeServiceImpl extends BaseServiceImpl<Employee,BaseSearchParameters>
        implements EmployeeService {

    /**
     * Empty constructor
     */
    public EmployeeServiceImpl() {
        super(Employee.class);
    }

    public Employee save(Employee employee) {
        entityManager.persist(employee);
        return employee;
    }

    public Employee update(Employee employee) throws Exception
    {
        Employee existing = entityManager.find(Employee.class,employee.getId());
        updateBasicProperties(employee,existing);

        entityManager.merge(existing);

        return employee;
    }


}
