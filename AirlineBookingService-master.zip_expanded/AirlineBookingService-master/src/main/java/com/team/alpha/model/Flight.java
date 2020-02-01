package com.team.alpha.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tbl_flights")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Flight implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	 
	@ManyToOne
	@JoinColumn(name="departure")
	private Airport departureAirport;

	@ManyToOne
	@JoinColumn(name="destination")
	private Airport destination;
	
	@Column(name="departure_date")
	private LocalDate departureDate;
	
	@Column(name="arrival_date")
	private LocalDate arrivalDate;
	
	@Column(name="flight_number")
	private int flightNumber;
	
	public Flight() {
	}

	public Flight(final int id, final Airport departureAirport,final Airport destination, final LocalDate departureDate,
			final LocalDate arrivalDate, final int flightNumber) {
		this.id = id;
		this.departureAirport = departureAirport;
		this.destination = destination;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.flightNumber = flightNumber;
	}

	public int getId() {
		return id;
	}

	public Airport getDepartureAirport() {
		return departureAirport;
	}

	public Airport getDestination() {
		return destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public int getFlightNumber() {
		return flightNumber;
	}
	
	@Override
	public int hashCode() {
		return id;
		
		
	}
		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof Flight) {
				return id == ((Flight) obj).getId()
						&& Objects.equals(departureAirport, ((Flight) obj).getDepartureAirport())
						&& Objects.equals(departureDate, ((Flight) obj).getDepartureDate())
						&& Objects.equals(destination, ((Flight) obj).getDestination())
						&& Objects.equals(arrivalDate, ((Flight) obj).getArrivalDate());
			} else {
				return false;
			}
		}
		
		
}
