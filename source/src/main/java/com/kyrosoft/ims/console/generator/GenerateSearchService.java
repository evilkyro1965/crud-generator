package com.kyrosoft.ims.console.generator;

import com.kyrosoft.ims.model.Employee;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import sun.nio.cs.Surrogate;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 4/17/2016.
 */
public class GenerateSearchService {

    private String searchCriteriaSourceDir = GeneratorConfig.searchCriteriaSourceDir;

    private String searchServiceImplSourceDir = GeneratorConfig.searchServiceImplSourceDir;

    private String searchServiceInterfaceSourceDir = GeneratorConfig.searchServiceInterfaceSourceDir;

    private String templatePath = GeneratorConfig.templatePath;

    private String relativeToSourcePath = GeneratorConfig.relativeToSourcePath;

    private String MODEL_PACKAGE = GeneratorConfig.MODEL_PACKAGE;

    private String DTO_PACKAGE = GeneratorConfig.DTO_PACKAGE;

    private String SERVICE_PACKAGE = GeneratorConfig.SERVICE_PACKAGE;

    private String SERVICE_IMPLEMENTATION_PACKAGE = GeneratorConfig.SERVICE_IMPLEMENTATION_PACKAGE;

    protected Class<?>[] supportedPrimitives = GeneratorConfig.supportedPrimitives;

    protected final String SQL_EQUAL_OPERATOR = "=";

    protected final String SQL_LIKE_OPERATOR = "LIKE";

    protected final String SQL_GE_OPERATOR = ">=";

    protected final String SQL_GT_OPERATOR = ">";

    protected final String SQL_LE_OPERATOR = "<=";

    protected final String SQL_LT_OPERATOR = "<";

    private Configuration cfg;

    private URI templateUri;

    private List<Class<?>> classList = new ArrayList<Class<?>>();

    public GenerateSearchService() throws Exception {
        /* ------------------------------------------------------------------------ */
        /* You should do this ONLY ONCE in the whole application life-cycle:        */
        URL url = ClassLoader.getSystemResource("");
        templateUri = url.toURI();
        String path = templateUri.getPath() + templatePath;

        /* Create and adjust the configuration singleton */
        cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(path));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
    }

    public void generateServiceSource(Map<String, Object> root, String fileOutput) throws Exception {

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("SearchServiceTemplate.template");

        /* Merge data-model with template */
        String outputPath = templateUri.getPath() + relativeToSourcePath + searchServiceInterfaceSourceDir + fileOutput;
        OutputStream outputStream = new FileOutputStream(outputPath);
        Writer out = new OutputStreamWriter(outputStream);
        temp.process(root, out);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }

    public void generateServiceImplSource(Map<String, Object> root, String fileOutput) throws Exception {

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("SearchServiceImplementation.template");

        /* Merge data-model with template */
        String outputPath = templateUri.getPath() + relativeToSourcePath + searchServiceImplSourceDir + fileOutput;
        OutputStream outputStream = new FileOutputStream(outputPath);
        Writer out = new OutputStreamWriter(outputStream);
        temp.process(root, out);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }

    public List<Class<?>> getClassList() {
        return classList;
    }

    public void setClassList(List<Class<?>> classList) {
        this.classList = classList;
    }

    public void generateSearchServiceImplFromSource() throws Exception {

        Map<String, Object> root = new HashMap<String, Object>();
        List<ClassWrapper> classWrappers = new ArrayList<ClassWrapper>();

        List<String> imports = new ArrayList<String>();
        imports.add(MODEL_PACKAGE+"*");
        imports.add(DTO_PACKAGE+"*");
        imports.add(SERVICE_PACKAGE+"*");
        imports.add("java.util.*");

        for (Class<?> clazz : classList) {
            ClassWrapper classWrapper = new ClassWrapper();
            classWrapper.setClassName(clazz.getSimpleName());

            List<PropertyImplWrapper> properties = new ArrayList<PropertyImplWrapper>();

            Field[] fields = FieldUtils.getAllFields(clazz);
            for(Field field: fields) {
                field.setAccessible(true);
                if (field.getType().getName().indexOf("java.lang.") >= 0) {
                    if (ArrayUtils.contains(supportedPrimitives, field.getType())) {
                        if (field.getType().getSimpleName().equals("String")) {
                            generateStringSearchProperty(field, properties);
                        }
                        else if(field.getType().getSimpleName().equals("Boolean")) {
                            generateBooleanSearchProperty(field, properties);
                        }
                        else if(
                                field.getType().getSimpleName().equals("Integer") ||
                                        field.getType().getSimpleName().equals("Long") ||
                                        field.getType().getSimpleName().equals("Float") ||
                                        field.getType().getSimpleName().equals("Double") ||
                                        field.getType().getSimpleName().equals("Short")
                                ) {
                            generateIntegerSearchProperty(field, properties);
                        }
                    }
                }
                else if(field.getType().getName().indexOf(MODEL_PACKAGE) >= 0) {
                    if(field.getType().isEnum()) {
                        generateBooleanSearchProperty(field, properties);
                    }
                }
                else if(field.getType().getName().equals("java.util.Date")) {
                    generateIntegerSearchProperty(field, properties);
                }
            }
            classWrapper.setProperties(properties);

            classWrappers.add(classWrapper);
        }
        root.put("classWrappers",classWrappers);
        root.put("imports",imports);
        generateServiceImplSource(root,"BaseSearchServiceImpl.java");
    }

    public void generateSearchServiceInterfaceFromSource() throws Exception {

        Map<String, Object> root = new HashMap<String, Object>();
        List<ClassWrapper> classWrappers = new ArrayList<ClassWrapper>();

        List<String> imports = new ArrayList<String>();
        imports.add(MODEL_PACKAGE+"*");
        imports.add(DTO_PACKAGE+"*");
        imports.add("java.util.*");

        for (Class<?> clazz : classList) {
            ClassWrapper classWrapper = new ClassWrapper();
            classWrapper.setClassName(clazz.getSimpleName());

            classWrappers.add(classWrapper);
        }

        root.put("imports",imports);
        root.put("classWrappers",classWrappers);
        generateServiceSource(root,"BaseSearchService.java");
    }

    public void generateStringSearchProperty(Field field,List<PropertyImplWrapper> properties) {
        //Equals property
        String equalPropertyType = field.getType().getSimpleName();
        String equalPropertyName = field.getName();
        String equalCriteriaName = field.getName() + "_eq";
        String equalCriteriaNameCap = capitalize(equalCriteriaName);
        properties.add(new PropertyImplWrapper(equalPropertyType,equalPropertyName,equalCriteriaName,equalCriteriaNameCap,SQL_EQUAL_OPERATOR));

        //Like with property
        String likePropertyType = field.getType().getSimpleName();
        String likePropertyName = field.getName();
        String likeCriteriaName = field.getName() + "_like";
        String likeCriteriaNameCap = capitalize(likeCriteriaName);
        properties.add(new PropertyImplWrapper(likePropertyType,likePropertyName,likeCriteriaName,likeCriteriaNameCap,SQL_LIKE_OPERATOR));
    }

    public void generateIntegerSearchProperty(Field field,List<PropertyImplWrapper> properties) {
        //Equals property
        String equalPropertyType = field.getType().getSimpleName();
        String equalPropertyName = field.getName();
        String equalCriteriaName = field.getName() + "_eq";
        String equalCriteriaNameCap = capitalize(equalCriteriaName);
        properties.add(new PropertyImplWrapper(equalPropertyType,equalPropertyName,equalCriteriaName,equalCriteriaNameCap,SQL_EQUAL_OPERATOR));

        //Greater Equal propety
        String gePropertyType = field.getType().getSimpleName();
        String gePropertyName = field.getName();
        String geCriteriaName = field.getName() + "_ge";
        String geCriteriaNameCap = capitalize(geCriteriaName);
        properties.add(new PropertyImplWrapper(gePropertyType,gePropertyName,geCriteriaName,geCriteriaNameCap,SQL_GE_OPERATOR));

        //Greater Than propety
        String gtPropertyType = field.getType().getSimpleName();
        String gtPropertyName = field.getName();
        String gtCriteriaName = field.getName() + "_gt";
        String gtCriteriaNameCap = capitalize(gtCriteriaName);
        properties.add(new PropertyImplWrapper(gtPropertyType,gtPropertyName,gtCriteriaName,gtCriteriaNameCap,SQL_GT_OPERATOR));

        //Less Equal propety
        String lePropertyType = field.getType().getSimpleName();
        String lePropertyName = field.getName();
        String leCriteriaName = field.getName() + "_le";
        String leCriteriaNameCap = capitalize(leCriteriaName);
        properties.add(new PropertyImplWrapper(lePropertyType,lePropertyName,leCriteriaName,leCriteriaNameCap,SQL_LE_OPERATOR));

        //Less Than propety
        String ltPropertyType = field.getType().getSimpleName();
        String ltPropertyName = field.getName();
        String ltCriteriaName = field.getName() + "_lt";
        String ltCriteriaNameCap = capitalize(ltCriteriaName);
        properties.add(new PropertyImplWrapper(ltPropertyType,ltPropertyName,ltCriteriaName,ltCriteriaNameCap,SQL_LT_OPERATOR));
    }

    public void generateBooleanSearchProperty(Field field,List<PropertyImplWrapper> properties) {
        //Equals property
        String equalPropertyType = field.getType().getSimpleName();
        String equalPropertyName = field.getName();
        String equalCriteriaName = field.getName() + "_eq";
        String equalCriteriaNameCap = capitalize(equalCriteriaName);
        properties.add(new PropertyImplWrapper(equalPropertyType,equalPropertyName,equalCriteriaName,equalCriteriaNameCap,SQL_EQUAL_OPERATOR));
    }

    private String capitalize(String input) {
        String output = input.substring(0, 1).toUpperCase() + input.substring(1);
        return output;
    }

}
