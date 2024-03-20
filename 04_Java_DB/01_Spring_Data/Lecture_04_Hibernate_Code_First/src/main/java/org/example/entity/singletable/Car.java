/**
 * Strategy: Single Table
 */

package org.example.entity.singletable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "car")
public class Car extends PassengerVehicle {
    private static final String TYPE = "CAR";

    public Car() {
    }

    public Car(int numOfPassengers) {
        super(TYPE, numOfPassengers);
    }
}