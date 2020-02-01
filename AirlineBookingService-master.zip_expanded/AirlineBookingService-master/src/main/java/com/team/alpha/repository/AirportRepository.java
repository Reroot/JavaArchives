package com.team.alpha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.Airport;


@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
	
	@Query(value="from Airport where name=:name")
	List<Airport> searchByName(@Param("name") String name);
}
