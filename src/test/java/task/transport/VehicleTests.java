package task.transport;

import org.junit.jupiter.api.Test;
import task.transport.exceptions.NoFreeSeatException;
import task.transport.exceptions.PassengerNotFoundException;
import task.transport.people.Firefighter;
import task.transport.people.Human;
import task.transport.people.Policeman;
import task.transport.road.Road;
import task.transport.vehicles.Bus;
import task.transport.vehicles.FireTruck;
import task.transport.vehicles.PoliceCar;
import task.transport.vehicles.Taxi;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTests {

    @Test
    void busAndTaxiCanCarryAnyone() {
        Bus bus = new Bus(2);
        Taxi taxi = new Taxi(2);

        Human regular = new Human("Ivan");
        Firefighter ff = new Firefighter("Oleh");
        Policeman pm = new Policeman("Serhii");

        bus.board(regular);
        bus.board(ff);
        assertEquals(2, bus.getOccupiedSeats());

        taxi.board(pm);
        taxi.board(regular);
        assertEquals(2, taxi.getOccupiedSeats());
    }

    @Test
    void boardingWhenFullThrows() {
        Taxi taxi = new Taxi(1);
        taxi.board(new Human("A"));
        assertThrows(NoFreeSeatException.class, () -> taxi.board(new Human("B")));
    }

    @Test
    void alightUnknownPassengerThrows() {
        Bus bus = new Bus(2);
        Human a = new Human("A");
        Human b = new Human("B");
        bus.board(a);
        assertThrows(PassengerNotFoundException.class, () -> bus.alight(b));
    }

    @Test
    void roadCountsAllHumansAcrossDifferentVehicleTypes() {
        Road road = new Road();

        Bus bus = new Bus(3);
        Taxi taxi = new Taxi(2);
        FireTruck ft = new FireTruck(2);
        PoliceCar pc = new PoliceCar(2);

        bus.board(new Human("H1"));
        bus.board(new Firefighter("F1"));

        taxi.board(new Human("H2"));

        ft.board(new Firefighter("F2"));
        ft.board(new Firefighter("F3"));

        pc.board(new Policeman("P1"));

        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(ft);
        road.addCarToRoad(pc);

        assertEquals(6, road.getCountOfHumans());
    }
}
