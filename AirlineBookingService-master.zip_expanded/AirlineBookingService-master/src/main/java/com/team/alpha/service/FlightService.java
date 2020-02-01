package com.team.alpha.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.model.Airport;
import com.team.alpha.model.Flight;
import com.team.alpha.repository.AirportRepository;
import com.team.alpha.repository.FlightRepository;

@Service
public class FlightService {
	
	@Autowired
	private FlightRepository flightDao;
	
	@Autowired
	private AirportRepository airportDao;
	
	public List<Flight> getFlight() {
		List<Flight> list = flightDao.findAll();
		return list;
	}
	
	public Flight findFlight(String departureAirport, String destination, LocalDate departureDate, LocalDate arrivalDate) {
		Airport tempAirport1 = airportDao.getOne(departureAirport);
		Airport tempAirport2 = airportDao.getOne(destination);
		Flight tempFlight = flightDao.findByFlightProps(tempAirport1, tempAirport2, departureDate, arrivalDate);
		return tempFlight;
	}

	public List<Flight> getDepartureFlights(String code) {	
		List<Flight> flightList = flightDao.findByDepartureCode(code);
		if(flightList.size() == 0) {
			return null;
		} else {
			return flightList;
		}
		 
	}
	
	public List<Flight> getDestinationFlights(String code) {
		List<Flight> flightList = flightDao.findByDestinationCode(code);
		if(flightList.size() == 0) {
			return null;
		} else {
			return flightList;
		}
	}
}
