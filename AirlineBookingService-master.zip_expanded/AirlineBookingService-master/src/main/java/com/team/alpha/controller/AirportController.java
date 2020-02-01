package com.team.alpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.team.alpha.model.Airport;
import com.team.alpha.service.AirportService;

@RestController
@RequestMapping(path="/search/api/")
public class AirportController {
	
	@Autowired
	private AirportService airportService;

	@GetMapping(value="/airport/")
	public ResponseEntity<List<Airport>> findAirports()	{
		List<Airport> airports = airportService.getAirports();
		if (airports.isEmpty()) {
            return new ResponseEntity<List<Airport>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);	
	}
	
	@GetMapping(value="/airport/by/{name}")
	public ResponseEntity<Airport> findAirport(@PathVariable(value="name") String name) {
		Airport airport = airportService.findAirportByName(name);
		if(airport == null) {
			return new ResponseEntity<Airport>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Airport>(airport, HttpStatus.OK);
	}
}
