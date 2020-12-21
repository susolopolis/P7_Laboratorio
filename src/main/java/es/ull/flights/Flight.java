/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package es.ull.flights;

import es.ull.passengers.Passenger;
/*!\brief

 *Clase Flight: Definicion y funciones asociadas
 *
 *\date		20 de Diciembre de 2020
 *\author		Jesus Navarro Hernadez
 */

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {

    private String flightNumber;
    private int seats;
    private Set<Passenger> passengers = new HashSet<>();

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
    private static Pattern pattern = Pattern.compile(flightNumberRegex);


    /*!\brief Constructor del objeto Flight
     *Inicializa las variables flightNumber y seats en funcion de las proporcionadas
     *
     *\param[in]	flightNumber    numero de vuelo
     *\param[in]	seats   numero de asientos disponibles
     *\param[throw]	RuntimeException	Excpecion producida por un numero de vuelo no valido
     *
     *\return		Si todo va bien, nada. Si se proporciona un numero de vuelo no valido, una expecion
     */

    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /*!\brief Funcion getFlightNumber
     *Devuelve el numero de vuelo
     *
     *\param[out]	flightNumber	numero de vuelo
     *
     *\return		Devolvera el numero de vuelo del objeto
     */

    public String getFlightNumber() {
        return flightNumber;
    }

    /*!\brief Funcion getNumberOfPassengers
     *Devuelve el numero de pasajeros
     *
     *\param[out]	passengers.size	dimension del vector de pasajeros
     *
     *\return		Devolvera el numero exacto de pasajeros que hay en el vuelo
     */


    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /*!\brief Funcion addPassenger
     *Agrega el pasajero al vuelo
     *
     * \param[in]	passenger	nuevo pasajero a agregar
     *
     *
     *\param[throw]	RuntimeException    Excepcion si no hay suficientes sitios
     *
     *\return		En caso satisfactorio, nada. En caso de no haber sitios disponibles, una expecion.
     */

    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /*!\brief Funcion removePassenger
     *Elimina al pasajero del vuelo
     *
     * \param[in]	passenger	pasajero a eliminar
     *
     *\return		Nada
     */


    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }

}

