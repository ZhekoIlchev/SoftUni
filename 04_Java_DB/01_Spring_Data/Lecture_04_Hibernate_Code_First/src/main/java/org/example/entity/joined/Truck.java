/**
 * Strategy: Joined
 */

//package org.example.entity.joined;
//
//import org.example.entity.singletable.TransportationVehicle;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "trucks")
//public class Truck extends TransportationVehicle {
//    private static final String TYPE = "TRUCK";
//
//    private int numOfContainers;
//
//    public Truck() {
//    }
//
//    public Truck(int loadCapacity, int numOfContainers) {
//        super(TYPE, loadCapacity);
//        this.numOfContainers = numOfContainers;
//    }
//
//    public int getNumOfContainers() {
//        return numOfContainers;
//    }
//
//    public void setNumOfContainers(int numOfContainers) {
//        this.numOfContainers = numOfContainers;
//    }
//}