package com.kyrosoft.ims.console.generator;

import com.kyrosoft.ims.model.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 4/17/2016.
 */
public class MainGenerator {

    public static void main(String[] args) throws Exception {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        classList.add(Employee.class);

        GenerateSearchCriteria generatorCriteria =  new GenerateSearchCriteria();
        generatorCriteria.setClassList(classList);

        GenerateSearchService generatorService = new GenerateSearchService();
        generatorService.setClassList(classList);

        generatorCriteria.generateSearchCriteriaFromSource();
        generatorService.generateSearchServiceInterfaceFromSource();
        generatorService.generateSearchServiceImplFromSource();
    }
}
