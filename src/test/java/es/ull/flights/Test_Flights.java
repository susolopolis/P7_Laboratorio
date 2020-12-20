package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Test_Flights {

    Flight testing = new Flight("AA9020", 100);
    Passenger example = new Passenger("1","Paco","US");

    @Test
    public void test_add_Passenger(){
        System.out.println("Testing adding Passenger...");
        assertTrue(testing.addPassenger(example));
    }

    @Test
    public void test_remove_Passenger(){
        System.out.println("Testing removing Passenger...");
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
}
