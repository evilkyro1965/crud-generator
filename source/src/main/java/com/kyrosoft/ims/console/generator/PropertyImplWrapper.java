package com.kyrosoft.ims.console.generator;

/**
 * Created by Administrator on 4/17/2016.
 */
public class PropertyImplWrapper {
    private String propertyType;

    private String propertyName;

    private String criteriaName;

    private String criteriaNameCapitalized;

    private String operator;

    public PropertyImplWrapper(String propertyType,String propertyName, String criteriaName, String criteriaNameCapitalized, String operator) {
        this.propertyType = propertyType;
        this.propertyName = propertyName;
        this.criteriaName = criteriaName;
        this.criteriaNameCapitalized = criteriaNameCapitalized;
        this.operator = operator;
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

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public String getCriteriaNameCapitalized() {
        return criteriaNameCapitalized;
    }

    public void setCriteriaNameCapitalized(String criteriaNameCapitalized) {
        this.criteriaNameCapitalized = criteriaNameCapitalized;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
