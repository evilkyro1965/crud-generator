package com.kyrosoft.ims.console.generator;

import com.kyrosoft.ims.model.Employee;
import com.kyrosoft.ims.model.dto.BaseSearchParameters;
import freemarker.template.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.io.*;

/**
 * Created by Administrator on 4/16/2016.
 */
public class GenerateSearchCriteria {

    private String searchCriteriaSourceDir = "src/main/java/com/kyrosoft/ims/model/dto/";

    private String templatePath = "/generator-templates";

    private String relativeToSourcePath = "../../";

    protected String MODEL_PACKAGE = "com.kyrosoft.ims.model.";

    /*
    protected Class<?>[] supportedPrimitives = new Class<?>[] {
            Boolean.class,
            Double.class,
            Enumeration.class,
            Float.class,
            Integer.class,
            Long.class,
            Short.class,
            String.class
    };
    */
    protected Class<?>[] supportedPrimitives = new Class<?>[] {
            Boolean.class,
            Integer.class,
            String.class
    };


    private Configuration cfg;

    private URI templateUri;

    public GenerateSearchCriteria() throws Exception {
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

    public void generateSource(Map<String, Object> root,String fileOutput) throws Exception {

        /* Get the template (uses cache internally) */
        Template temp = cfg.getTemplate("SearchCriteriaTemplate.template");

        /* Merge data-model with template */
        String outputPath = templateUri.getPath() + relativeToSourcePath + searchCriteriaSourceDir + fileOutput;
        OutputStream outputStream = new FileOutputStream(outputPath);
        Writer out = new OutputStreamWriter(outputStream);
        temp.process(root, out);
        // Note: Depending on what `out` is, you may need to call `out.close()`.
        // This is usually the case for file output, but not for servlet output.
    }

    public void generateSearchCriteriaFromSourceExample() throws Exception {
        /* Create a data-model */
        Map<String, Object> root = new HashMap<String, Object>();
        List<PropertyWrapper> properties = new ArrayList<PropertyWrapper>();
        properties.add(new PropertyWrapper("String","name",capitalize("name")));
        root.put("className", "SearchCriteria");
        root.put("properties", properties);

        String fileOutput = "output.txt";

        generateSource(root,fileOutput);
    }

    public void generateSearchCriteriaFromSourceSimple() throws Exception {
        /* Create a data-model */
        List<Class<?>> classList = new ArrayList<Class<?>>();
        classList.add(Employee.class);

        for (Class<?> clazz : classList) {
            Map<String, Object> root = new HashMap<String, Object>();
            List<PropertyWrapper> properties = new ArrayList<PropertyWrapper>();

            String className = clazz.getSimpleName()+"SearchCriteria";
            root.put("className", className);

            Field[] fields = FieldUtils.getAllFields(clazz);
            for(Field field: fields) {
                field.setAccessible(true);
                if (field.getType().getName().indexOf("java.lang.") >= 0) {
                    String propertyType = field.getType().getSimpleName();
                    String propertyName = field.getName();
                    String propertyNameCapitalized = capitalize(field.getName());
                    properties.add(new PropertyWrapper(propertyType, propertyName, propertyNameCapitalized));
                }
                else if(field.getType().getName().indexOf(MODEL_PACKAGE) >= 0) {

                }
            }
            root.put("properties", properties);
            generateSource(root,className+".java");
        }
    }

    public void generateSearchCriteriaFromSource() throws Exception {
        /* Create a data-model */
        List<Class<?>> classList = new ArrayList<Class<?>>();
        classList.add(Employee.class);

        for (Class<?> clazz : classList) {
            Map<String, Object> root = new HashMap<String, Object>();
            List<PropertyWrapper> properties = new ArrayList<PropertyWrapper>();

            String className = clazz.getSimpleName()+"SearchCriteria";
            root.put("className", className);

            Field[] fields = FieldUtils.getAllFields(clazz);
            for(Field field: fields) {
                field.setAccessible(true);
                if (field.getType().getName().indexOf("java.lang.") >= 0) {
                    if(ArrayUtils.contains( supportedPrimitives, field.getType() )) {
                        if(field.getType().getSimpleName().equals("String")) {
                            generateStringSearchProperty(field, properties);
                        }
                        else if(field.getType().getSimpleName().equals("Boolean")) {
                            generateBooleanSearchProperty(field, properties);
                        }
                        else if(field.getType().getSimpleName().equals("Integer")) {
                            generateIntegerSearchProperty(field, properties);
                        }
                    }
                }
                else if(field.getType().getName().indexOf(MODEL_PACKAGE) >= 0) {

                }
            }
            root.put("properties", properties);
            generateSource(root,className+".java");
        }
    }

    public void generateIntegerSearchProperty(Field field,List<PropertyWrapper> properties) {
        //Equals propety
        String equalPropertyType = field.getType().getSimpleName();
        String equalPropertyName = field.getName() + "_eq";
        String equalPropertyNameCap = capitalize(equalPropertyName);
        properties.add(new PropertyWrapper(equalPropertyType, equalPropertyName, equalPropertyNameCap));

        //Greater Equal propety
        String gePropertyType = field.getType().getSimpleName();
        String gePropertyName = field.getName() + "_ge";
        String gePropertyNameCap = capitalize(gePropertyName);
        properties.add(new PropertyWrapper(gePropertyType, gePropertyName, gePropertyNameCap));

        //Greater Then propety
        String gtPropertyType = field.getType().getSimpleName();
        String gtPropertyName = field.getName() + "_gt";
        String gtPropertyNameCap = capitalize(gtPropertyName);
        properties.add(new PropertyWrapper(gtPropertyType, gtPropertyName, gtPropertyNameCap));

        //Less Equal propety
        String lePropertyType = field.getType().getSimpleName();
        String lePropertyName = field.getName() + "_le";
        String lePropertyNameCap = capitalize(lePropertyName);
        properties.add(new PropertyWrapper(lePropertyType, lePropertyName, lePropertyNameCap));

        //Less Then propety
        String ltPropertyType = field.getType().getSimpleName();
        String ltPropertyName = field.getName() + "_lt";
        String ltPropertyNameCap = capitalize(equalPropertyName);
        properties.add(new PropertyWrapper(ltPropertyType, ltPropertyName, ltPropertyNameCap));
    }

    public void generateBooleanSearchProperty(Field field,List<PropertyWrapper> properties) {
        //Equals propety
        String equalPropertyType = field.getType().getSimpleName();
        String equalPropertyName = field.getName() + "_eq";
        String equalPropertyNameCap = capitalize(equalPropertyName);
        properties.add(new PropertyWrapper(equalPropertyType, equalPropertyName, equalPropertyNameCap));
    }

    public void generateStringSearchProperty(Field field,List<PropertyWrapper> properties) {
        //Equals propety
        String equalPropertyType = field.getType().getSimpleName();
        String equalPropertyName = field.getName() + "_eq";
        String equalPropertyNameCap = capitalize(equalPropertyName);
        properties.add(new PropertyWrapper(equalPropertyType,equalPropertyName,equalPropertyNameCap));

        //Start with propety
        String startPropertyType = field.getType().getSimpleName();
        String startPropertyName = field.getName() + "_start";
        String startPropertyNameCap = capitalize(startPropertyName);
        properties.add(new PropertyWrapper(startPropertyType,startPropertyName,startPropertyNameCap));

        //End with propety
        String endPropertyType = field.getType().getSimpleName();
        String endPropertyName = field.getName() + "_end";
        String endPropertyNameCap = capitalize(endPropertyName);
        properties.add(new PropertyWrapper(endPropertyType,endPropertyName,endPropertyNameCap));

        //Like with propety
        String likePropertyType = field.getType().getSimpleName();
        String likePropertyName = field.getName() + "_like";
        String likePropertyNameCap = capitalize(likePropertyName);
        properties.add(new PropertyWrapper(likePropertyType,likePropertyName,likePropertyNameCap));
    }

    public static void main(String[] args) throws Exception {
        GenerateSearchCriteria generator =  new GenerateSearchCriteria();
        generator.generateSearchCriteriaFromSource();
    }

    private String capitalize(String input) {
        String output = input.substring(0, 1).toUpperCase() + input.substring(1);
        return output;
    }
}
