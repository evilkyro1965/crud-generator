package com.kyrosoft.ims.model;

import javax.persistence.*;

/**
 * Created by Administrator on 4/15/2016.
 */
@MappedSuperclass
public class IdentifiableEntity {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Get the id
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Empty constructor
     */
    public IdentifiableEntity() {}

}
