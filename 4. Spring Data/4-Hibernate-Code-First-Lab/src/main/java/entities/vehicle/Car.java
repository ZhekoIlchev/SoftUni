package entities.vehicle;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {
    private static final String CAR_TYPE = "CAR";

    public Car() {
        super(CAR_TYPE);
    }
}