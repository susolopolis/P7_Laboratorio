package es.ull.flights;

import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*!\brief

 *Clase FlightTest: Todos los test para la clase Flight
 *
 *\date		20 de Diciembre de 2020
 *\author		Jesus Navarro Hernadez
 */
public class FlightTest {

    Flight testing = new Flight("AA9020", 100);
    Passenger example = new Passenger("1","Paco","US");

    /*!\brief Test para verificacion de funcion addPassenger
     *Comprobaremos si el resultado de meter al usuario con datos validos en el vuelo con datos validos es positivo
     *
     */
    @Test
    public void test_add_Passenger(){
        System.out.println("Testing adding Passenger...");
        assertTrue(testing.addPassenger(example));
    }
    /*!\brief Test para verificacion de funcion getFlightNumber
     *Comprobaremos si el numero de vuelo esperado (AA9020, segun el vuelo de ejemplo) coincide
     *
     */

    @Test
    public void test_get_Flight_Number(){
        System.out.println("Testing flight number...");
        assertEquals("AA9020", testing.getFlightNumber());
    }

    /*!\brief Test para verificacion de funcion getNumberOfPassengers
     *Comprobaremos si el numero de pasajeros esperado (0, segun el pasajero y el vuelo de ejemplo) coincide
     *
     */
    @Test
    public void test_get_Number_Passengers(){
        System.out.println("Testing number of passengers...");
        assertEquals(0, testing.getNumberOfPassengers());
    }

    /*!\brief Test para verificacion de funcion removePassenger
     *Comprobaremos si la eliminacion del pasajero agregado es positivo
     *
     */
    @Test
    public void test_remove_Passenger(){
        testing.addPassenger(example);
        assertTrue(testing.removePassenger(example));
    }

    /*!\brief Test para verificacion de un codigo de vuelo invalido
     *Comprobaremos si al crear un vuelo con un codigo invalido se genera la excepcion esperada
     *
     */

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

    /*!\brief Test para verificacion de un vuelo sin sitios disponibles
     *Comprobaremos si al intentar agregar un nuevo pasajero a un vuelo sin espacios, se genera la excepcion esperada
     *
     */

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
