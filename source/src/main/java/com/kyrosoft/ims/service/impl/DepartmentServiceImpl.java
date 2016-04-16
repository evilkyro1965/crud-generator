package com.kyrosoft.ims.service.impl;

import com.kyrosoft.ims.model.Department;
import com.kyrosoft.ims.model.Employee;
import com.kyrosoft.ims.model.dto.BaseSearchParameters;
import com.kyrosoft.ims.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 4/15/2016.
 */
@Service
@Transactional
public class DepartmentServiceImpl extends BaseServiceImpl<Department,BaseSearchParameters>
        implements DepartmentService {

    /**
     * Empty constructor
     */
    public DepartmentServiceImpl() {
        super(Department.class);
    }

    public Department save(Department department) {
        entityManager.persist(department);
        return department;
    }

}
