package task.transport.vehicles;

import task.transport.exceptions.NoFreeSeatException;
import task.transport.exceptions.PassengerNotFoundException;
import task.transport.people.Human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int maxSeats;
    private final List<T> passengers = new ArrayList<>();

    protected Vehicle(int maxSeats) {
        if (maxSeats <= 0) throw new IllegalArgumentException("maxSeats must be > 0");
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() { return maxSeats; }

    public int getOccupiedSeats() { return passengers.size(); }

    public List<T> getPassengers() { return Collections.unmodifiableList(passengers); }

    public void board(T passenger) {
        if (passengers.size() >= maxSeats) {
            throw new NoFreeSeatException("No free seats in " + getClass().getSimpleName());
        }
        passengers.add(passenger);
    }

    public void alight(T passenger) {
        boolean removed = passengers.remove(passenger);
        if (!removed) {
            throw new PassengerNotFoundException(passenger + " is not in " + getClass().getSimpleName());
        }
    }
}
