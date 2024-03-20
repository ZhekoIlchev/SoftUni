/**
 * Strategy: Table per class
 */

//package org.example.entity.tableperclass;
//
//import javax.persistence.Basic;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//
//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//public abstract class Vehicle {
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
//    private Long id;
//
//    @Basic
//    private String type;
//
//    public Vehicle() {
//    }
//
//    public Vehicle(String type) {
//        this.type = type;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//}