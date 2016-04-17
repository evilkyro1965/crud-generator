package com.kyrosoft.ims.console.generator;

import java.util.List;

/**
 * Created by Administrator on 4/17/2016.
 */
public class ClassWrapper {

    private String className;

    private List<PropertyImplWrapper> properties;

    public ClassWrapper(String className,List<PropertyImplWrapper> properties) {
        this.className = className;
        this.properties = properties;
    }

    public ClassWrapper() {

    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<PropertyImplWrapper> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyImplWrapper> properties) {
        this.properties = properties;
    }
}
