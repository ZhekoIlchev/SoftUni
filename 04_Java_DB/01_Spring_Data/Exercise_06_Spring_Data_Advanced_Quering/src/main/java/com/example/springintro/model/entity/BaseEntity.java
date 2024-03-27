package com.example.springintro.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    private Long id;

    /**
     * private String id;
     */

    private Date deletedOn;


    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Date deletedOn) {
        this.deletedOn = deletedOn;
    }

    /** Example of UUID Primary Key.
     @Id
     @GeneratedValue(generator = "uuid-str")
     @GenericGenerator(name = "uuid-str", strategy = "org.hibernate.id.UUIDGenerator")
     public String getId() {
     return id;
     }

     public void setId(String id) {
     this.id = id;
     }
     */
}