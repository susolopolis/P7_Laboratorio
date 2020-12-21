package es.ull.passengers;

import es.ull.flights.Flight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*!\brief

 *Clase PassengerTest: Todos los test para la clase Passenger
 *
 *\date		20 de Diciembre de 2020
 *\author		Jesus Navarro Hernadez
 */
public class PassengerTest {
    Passenger Pasajero = new Passenger("1","Paco","US");
    Flight testing = new Flight("AA9020", 100);

    /*!\brief Test para verificacion de funcion getName
     *Comprobaremos si el nombre esperado (Paco, segun el pasajero de ejemplo) coincide
     *
     */
    @Test
    public void test_get_Name(){
        System.out.println("Testing get Name...");
        assertEquals("Paco", Pasajero.getName());
    }

    /*!\brief Test para verificacion de funcion getIdentifier
     *Comprobaremos si el identificador esperado (1, segun el pasajero de ejemplo) coincide
     *
     */
    @Test
    public void test_get_Identifier(){
        System.out.println("Testing get Identifier...");
        assertEquals("1", Pasajero.getIdentifier());
    }
    /*!\brief Test para verificacion de funcion getCountryCode
     *Comprobaremos si el codigo de pais esperado (US, segun el pasajero de ejemplo) coincide
     *
     */
    @Test
    public void test_get_CountryCode(){
        System.out.println("Testing get Country Code...");
        assertEquals("US", Pasajero.getCountryCode());
    }

    /*!\brief Test para verificacion de funcion getFlight
     *Comprobaremos si el vuelo esperado (testing, segun el pasajero y el vuelo de ejemplo) coincide
     *
     */
    @Test
    public void test_Flight(){
        System.out.println("Testing get Flight...");
        Pasajero.setFlight(testing);
        assertEquals(testing, Pasajero.getFlight());
    }

    /*!\brief Test para verificacion de funcion joinFlight
     *En este caso, crearemos un nuevo vuelo e insertaremos al usuario en el. Finalmente, comprobaremos que en efecto el vuelo actual es el vuelo creado anteriormente
     *
     */
    @Test
    public void test_join_flight(){
        System.out.println("Testing join Flight...");
        Flight testing2 = new Flight("AA9023", 200);
        Pasajero.joinFlight(testing2);
        assertEquals(testing2, Pasajero.getFlight());
    }

    /*!\brief Test para verificacion de funcion toString
     *Comprobaremos si la informacion del pasajero de ejemplo coincide
     *
     */
    @Test
    public void test_to_string(){
        System.out.println("Testing toString...");
        assertEquals("Passenger Paco with identifier: 1 from US", Pasajero.toString());
    }

    /*!\brief Test para verificacion de un codigo de pais invalido
     *En este caso, crearemos un pasajero con un codigo de pais invalido, de manera que comprobaremos si se produce la excepcion esperada
     *
     */
    @Test
    public void test_invalid_coutry_code(){
        System.out.println("Testing Invalid Country Code...");
        try {
            Passenger pasajero = new Passenger("2", "Gilfredo", "Aleatorio");
        }
        catch (RuntimeException a){
            System.out.println("Excepcion");
        }
    }

    /*!\brief Test para verificacion de un fallo al unirse a un vuelo (1)
     *En este caso, crearemos un vuelo con un numero de asientos igual a 0 e intentaremos meter al pasajero. Comprobaremos que se genera la excepcion esperada
     *
     */
    @Test
    public void test_joinFlight_Invalid(){
        System.out.println("Testing joingFlight with errors 1...");
        Flight vuelo = new Flight("AA2010",0);

        try {
            Pasajero.joinFlight(vuelo);
        }
        catch (RuntimeException a){
            System.out.println("Excepcion joining flight 1...");
        }
    }
    /*!\brief Test para verificacion de un fallo al unirse a un vuelo (2)
     *En este caso, crearemos un vuelo con un numero de asientos igual a 0. Ademas, se le asignara un vuelo no existente e intentaremos meter al pasajero. Comprobaremos que se genera la excepcion
     *
     */
    @Test
    public void test_joinFlight_Invalid2(){
        System.out.println("Testing joingFlight with errors 2...");
        Flight vuelo = new Flight("AA2010",0);

        Pasajero.setFlight(null);
        try{
            Pasajero.joinFlight(vuelo);
        }catch (RuntimeException a){
            System.out.println("Excepcion joining flight 2...");
        }
    }
}
