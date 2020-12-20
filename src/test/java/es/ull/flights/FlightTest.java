package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FlightTest {

    Flight testing = new Flight("AA9020", 100);
    Passenger example = new Passenger("1","Paco","US");


    @Test
    public void test_add_Passenger(){
        System.out.println("Testing adding Passenger...");
        assertTrue(testing.addPassenger(example));
    }


    @Test
    public void test_get_Flight_Number(){
        System.out.println("Testing flight number...");
        assertEquals("AA9020", testing.getFlightNumber());
    }

    @Test
    public void test_get_Number_Passengers(){
        System.out.println("Testing number of passengers...");
        assertEquals(0, testing.getNumberOfPassengers());
    }

    @Test
    public void test_remove_Passenger(){
        testing.addPassenger(example);
        assertTrue(testing.removePassenger(example));
    }
    @Test
    public void test_invalid_flight_number(){
        System.out.println("Testing Invalid Flight Number..");
        try{
            Flight vuelo = new Flight("Holaaaa00",50);
        }
        catch(RuntimeException a){
            System.out.println("Excepcion");
        }
    }

    @Test
    public void test_not_enough_sites(){
        System.out.println("Testing not enough sites on the flight..");
        Flight vuelo = new Flight("AA2010",1);
        vuelo.addPassenger(example);

        Passenger pasajero = new Passenger("2", "Gilfredo", "US");

        try{
            vuelo.addPassenger(pasajero);
        }

        catch(RuntimeException a){
            System.out.println("Excepcion: Not enough sites left");
        }
    }
}
