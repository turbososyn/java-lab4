package task.transport.vehicles;

import task.transport.people.Human;

public abstract class Automobile<T extends Human> extends Vehicle<T> {
    protected Automobile(int maxSeats) { super(maxSeats); }
}
