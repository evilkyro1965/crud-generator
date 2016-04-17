package com.kyrosoft.ims.console.generator;

/**
 * Created by Administrator on 4/17/2016.
 */
public class GeneratorConfig {

    public static String searchCriteriaSourceDir = "src/main/java/com/kyrosoft/ims/model/dto/";

    public static String searchServiceImplSourceDir = "src/main/java/com/kyrosoft/ims/service/impl/";

    public static String searchServiceInterfaceSourceDir = "src/main/java/com/kyrosoft/ims/service/";

    public static String templatePath = "/generator-templates";

    public static String relativeToSourcePath = "../../";

    public static String MODEL_PACKAGE = "com.kyrosoft.ims.model.";

    public static String DTO_PACKAGE = "com.kyrosoft.ims.model.dto.";

    public static String SERVICE_PACKAGE = "com.kyrosoft.ims.service.";

    public static String SERVICE_IMPLEMENTATION_PACKAGE = "com.kyrosoft.ims.service.impl.";

    public static Class<?>[] supportedPrimitives = new Class<?>[] {
            Boolean.class,
            Double.class,
            Float.class,
            Integer.class,
            Long.class,
            Short.class,
            String.class
    };
}
