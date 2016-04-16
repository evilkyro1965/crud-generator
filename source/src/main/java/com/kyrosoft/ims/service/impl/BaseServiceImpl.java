package com.kyrosoft.ims.service.impl;

/**
 * Created by Administrator on 4/15/2016.
 */

import com.kyrosoft.ims.model.CrudOperation;
import com.kyrosoft.ims.model.IdentifiableEntity;
import com.kyrosoft.ims.model.dto.BaseSearchParameters;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


/**
 * BaseServiceImpl
 *
 * @author fahrur
 * @version 1.0
 */
public abstract class BaseServiceImpl<T extends IdentifiableEntity, S extends BaseSearchParameters> {

    /**
     * Represents the Java Class object for the entity's class.
     *
     * Non-null.
     *
     * It will be initialized to Class<T> in constructor and will not change afterwards.
     */
    private final Class<T> entityClass;

    /**
     * Represents the EntityManager used to access database persistence.
     *
     * Required. Not null.
     */
    @PersistenceContext
    protected EntityManager entityManager;

    protected String MODEL_PACKAGE = "com.kyrosoft.ims.model.";

    /**
     * The class name of the class extends this class.
     */
    protected final String CLASS_NAME = getClass().getName();

    /**
     * Constructor.
     *
     * @param entityClass
     *            the entity class
     * @throws IllegalArgumentException
     *             if entityClass is null.
     */
    protected BaseServiceImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Returns the entityManager.
     *
     * @return the entityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected void updateBasicProperties(T source, T target) throws Exception
    {
        Field[] fields = FieldUtils.getAllFields(entityClass);
        for(Field field: fields) {
            field.setAccessible(true);
            if(field.getType().getName().indexOf("java.lang.") >= 0) {
                Object value = FieldUtils.readField(field, source);
                FieldUtils.writeField(field, target, value);
            }
            else if(field.getType().getName().indexOf(MODEL_PACKAGE) >= 0) {
                String propertyName = field.getName();

                Object value = FieldUtils.readField(field, source);
                Long id = ((IdentifiableEntity) value).getId();
                if(id!=null) {
                    Object ori = entityManager.find(field.getType(),id);
                    FieldUtils.writeField(field, target, value);
                }
                else {
                    FieldUtils.writeField(field, target, null);
                }
            }
        }
    }


}
