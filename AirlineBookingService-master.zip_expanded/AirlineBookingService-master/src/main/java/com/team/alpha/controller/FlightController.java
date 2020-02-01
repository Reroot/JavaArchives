package com.team.alpha.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.alpha.model.Flight;
import com.team.alpha.service.FlightService;

@RestController
@RequestMapping(path="/search")
public class FlightController {
	
	@Autowired
	private FlightService flightService;
	
	@GetMapping(value="/flights/")
	public ResponseEntity<List<Flight>> findFlights()	{
		List<Flight> flights = flightService.getFlight();
		if (flights.isEmpty()) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);	
	}
	
	@GetMapping(value="/flight/{departureAirport}/{destination}/{departureDate}/{arrivalDate}")
	public ResponseEntity<Flight> findFlight(
			@PathVariable(value="departureAirport") String departureAirport, 
			@PathVariable(value="destination") String destination, 
			@PathVariable(value="departureDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
			@PathVariable(value="arrivalDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate  arrivalDate) {
		Flight flight = flightService.findFlight(departureAirport, destination, departureDate, arrivalDate);
		if (flight == null) {
            return new ResponseEntity<Flight>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<Flight>(flight, HttpStatus.OK);	
	}
	
	@GetMapping(value="/flight/departure/{code}")
	public ResponseEntity<List<Flight>> findDepartureFlight(@PathVariable(value="code") String code) {
		List<Flight> flights = flightService.getDepartureFlights(code);
		if (flights.isEmpty()) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);	
	}
	
	@GetMapping(value="/flight/destination/{code}")
	public ResponseEntity<List<Flight>> findDestinationFlight(@PathVariable(value="code") String code) {
		List<Flight> flights = flightService.getDestinationFlights(code);
		if (flights.isEmpty()) {
            return new ResponseEntity<List<Flight>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Flight>>(flights, HttpStatus.OK);	
	}
}
