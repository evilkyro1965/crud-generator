package com.kyrosoft.ims.service;

import com.kyrosoft.ims.model.*;
import com.kyrosoft.ims.model.dto.*;
import java.util.*;

public interface BaseSearchService {

    public SearchResult<Employee> search(EmployeeSearchCriteria criteria) throws Exception;

}
