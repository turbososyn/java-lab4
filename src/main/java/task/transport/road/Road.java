package task.transport.road;

import task.transport.people.Human;
import task.transport.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Road {
    private final List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public List<Vehicle<? extends Human>> getCarsInRoad() {
        return Collections.unmodifiableList(carsInRoad);
    }

    public void addCarToRoad(Vehicle<? extends Human> vehicle) {
        carsInRoad.add(vehicle);
    }

    public int getCountOfHumans() {
        int sum = 0;
        for (Vehicle<? extends Human> v : carsInRoad) {
            sum += v.getOccupiedSeats();
        }
        return sum;
    }
}
