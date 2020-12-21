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
package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;
/*!\brief

 *Clase Passenger: Definicion y funciones asociadas
 *
 *\date		20 de Diciembre de 2020
 *\author		Jesus Navarro Hernadez
 */
public class Passenger {

    private String identifier;
    private String name;
    private String countryCode;
    private Flight flight;

    /*!\brief Constructor del objeto Passenger
     *Inicializa las variables identifier, name y countryCode en funcion de las proporcionadas
     *
     *\param[in]	identifier  identificador del pasajero
     *\param[in]	name    nombre del pasajero
     * \param[in]	countryCode codigo del pais del pasajero
     *\param[throw]	RuntimeException	Excepcion si el codigo del pais es invalido
     *
     *\return		Si todo va bien, nada. Si el codigo del pais es invalido (no formato ISO), una expecion
     */

    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /*!\brief Funcion getIdentifier
     *Devuelve el identificador
     *
     *\param[out]	identifier  identificador del usuario
     *
     *\return		Identificador propio del pasajero
     */

    public String getIdentifier() {
        return identifier;
    }

    /*!\brief Funcion getName
     *Devuelve el nombre
     *
     *\param[out]	name    nombre del pasajero
     *
     *\return		nombre del pasajero
     */
    public String getName() {
        return name;
    }

    /*!\brief Funcion getCountryCode
     *Devuelve el codigo del pais
     *
     *\param[out]	countryCode codigo ISO del pais
     *
     *\return		Codigo en formato ISO del pais del pasajero
     */

    public String getCountryCode() {
        return countryCode;
    }

    /*!\brief Funcion getFlight
     *Devuelve el vuelo
     *
     *\param[out]	flight  vuelo del pasajero
     *
     *\return		objeto del tipo Flight propio del pasajero
     */

    public Flight getFlight() {
        return flight;
    }

    /*!\brief Funcion joinFlight
     *Establecera un nuevo vuelo para el pasajero
     *
     *\param[in]	flight  nuevo vuelo
     *\param[throw]	RuntimeException    excepcion al no poder eliminar o agregar al pasajero del vuelo
     *
     *\return		Si todo va bien, no devolvera nada. En caso de no poder eliminar al pasajero del vuelo actualmente asignado o no poder agregarlo, una excpecion
     */

    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /*!\brief Funcion setFlight
     *Establece como vuelo el parametro recibido
     *
     *\param[in]	flight  nuevo vuelo
     *
     */

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /*!\brief Funcion toString
     *Devuelve la informacion del pasajero
     *
     *\param[out]	String  cadena con la informacion del pasajero
     *
     *\return		Cadena que contendra el nombre, identificador y pais del pasajero
     */

    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}

