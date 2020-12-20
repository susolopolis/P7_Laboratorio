package es.ull.passengers;


import es.ull.flights.Flight;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_Passengers {
    Passenger Pasajero = new Passenger("1","Paco","US");
    Flight testing = new Flight("AA9020", 100);


    @Test
    public void test_get_Name(){
        System.out.println("Testing get Name...");
        assertEquals("Paco", Pasajero.getName());
    }

    @Test
    public void test_get_Identifier(){
        System.out.println("Testing get Identifier...");
        assertEquals("1", Pasajero.getIdentifier());
    }

    @Test
    public void test_get_CountryCode(){
        System.out.println("Testing get Country Code...");
        assertEquals("US", Pasajero.getCountryCode());
    }

    @Test
    public void test_Flight(){
        System.out.println("Testing get Flight...");
        Pasajero.setFlight(testing);
        assertEquals(testing, Pasajero.getFlight());
    }

    @Test
    public void test_join_flight(){
        System.out.println("Testing join Flight...");
        Flight testing2 = new Flight("AA9023", 200);
        Pasajero.joinFlight(testing2);
        assertEquals(testing2, Pasajero.getFlight());
    }

    @Test
    public void test_to_string(){
        System.out.println("Testing toString...");
        assertEquals("Passenger Paco with identifier: 1 from US", Pasajero.toString());
    }
}
