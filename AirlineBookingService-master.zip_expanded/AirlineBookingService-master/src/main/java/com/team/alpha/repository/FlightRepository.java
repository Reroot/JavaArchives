package com.team.alpha.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.Airport;
import com.team.alpha.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
	
	@Query(value="from Flight where id=:id")
	List<Flight> findByFlightNumber(int id);
	
	@Query(value="from Flight where departureAirport=:departureAirport and destination=:destination "
			+ "and departureDate=:departureDate and arrivalDate=:arrivalDate")
	Flight findByFlightProps(@Param("departureAirport") Airport departureAirport, 
			@Param("destination") Airport destination, 
			@Param("departureDate") LocalDate departureDate, 
			@Param("arrivalDate") LocalDate arrivalDate);

	@Query(value="from Flight where departureAirport=ANY(select code from Airport where name LIKE CONCAT(:departure,'%'))")
	List<Flight> findByDepartureCode(@Param("departure") String departure);

	@Query(value="from Flight where destination=ANY(select code from Airport where name LIKE CONCAT(:destination,'%'))")
	List<Flight> findByDestinationCode(@Param("destination") String destination);
}
