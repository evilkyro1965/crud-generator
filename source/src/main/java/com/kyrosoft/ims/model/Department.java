package com.kyrosoft.ims.model;

import javax.persistence.*;

/**
 * Created by Administrator on 4/15/2016.
 */
@Entity(name = "department")
public class Department extends IdentifiableEntity {

    @Basic
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department() {}
}
