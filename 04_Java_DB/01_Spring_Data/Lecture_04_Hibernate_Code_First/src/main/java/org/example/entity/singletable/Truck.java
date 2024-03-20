/**
 * Strategy: Single Table
 */

package org.example.entity.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "truck")
public class Truck extends TransportationVehicle {
    private static final String TYPE = "TRUCK";

    private int numOfContainers;

    public Truck() {
    }

    public Truck(int loadCapacity, int numOfContainers) {
        super(TYPE, loadCapacity);
        this.numOfContainers = numOfContainers;
    }

    public int getNumOfContainers() {
        return numOfContainers;
    }

    public void setNumOfContainers(int numOfContainers) {
        this.numOfContainers = numOfContainers;
    }
}