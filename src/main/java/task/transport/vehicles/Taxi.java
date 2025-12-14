package task.transport.vehicles;

import task.transport.people.Human;

public class Taxi extends Automobile<Human> {
    public Taxi(int maxSeats) { super(maxSeats); }
}
