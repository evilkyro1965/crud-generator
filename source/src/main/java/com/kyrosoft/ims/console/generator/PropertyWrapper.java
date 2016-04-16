package com.kyrosoft.ims.console.generator;

/**
 * Created by Administrator on 4/16/2016.
 */
public class PropertyWrapper {

    private String propertyType;

    private String propertyName;

    private String propertyNameCapitalized;

    public PropertyWrapper(String propertyType,String propertyName, String propertyNameCapitalized) {
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.propertyNameCapitalized = propertyNameCapitalized;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyNameCapitalized() {
        return propertyNameCapitalized;
    }

    public void setPropertyNameCapitalized(String propertyNameCapitalized) {
        this.propertyNameCapitalized = propertyNameCapitalized;
    }
}
