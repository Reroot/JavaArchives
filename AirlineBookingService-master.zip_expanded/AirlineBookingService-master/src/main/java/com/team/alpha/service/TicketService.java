package com.team.alpha.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.team.alpha.controller.TicketController;
import com.team.alpha.model.Airport;
import com.team.alpha.model.Flight;
import com.team.alpha.model.SeatAspects;
import com.team.alpha.model.Ticket;
import com.team.alpha.model.User;
import com.team.alpha.repository.AirportRepository;
import com.team.alpha.repository.FlightRepository;
import com.team.alpha.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketDao;
	
	@Autowired
	private FlightRepository flightDao;
	
	@Autowired
	private AirportRepository airportDao;
	
	private final int defaultBookingExpiration=10;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketService.class);
	
	public List<Ticket> findAllTickets() {		
		return ticketDao.findAll();		
	}


	public Ticket getTicket(final SeatAspects seat) {
		return ticketDao.findById(seat).get();
	}
	

	public Ticket getBooking(final String bookingId) {
		List<Ticket> list = ticketDao.findByBookingId(bookingId);
		if (list.isEmpty()) {
			return null;
		} else if (list.size() > 1) {
			throw new IllegalStateException("Uniqueness constraint violated");
		} else {
			return list.get(0);
		}
	}
	
	public Ticket bookTicket(final SeatAspects seat, final User user) {	
		return bookTicket(seat, user,
				LocalDateTime.now().plusMinutes(defaultBookingExpiration));		
	}

	@Transactional
	public Ticket bookTicket(final SeatAspects seat, final User user, final LocalDateTime timeout) {	
		Ticket ticket = getTicket(seat);
//		System.out.println(ticket.toString());
//		System.out.println(user.toString());
//		System.out.println(timeout);
		if (ticket.getReserver() != null) {
			throw new IllegalArgumentException("Ticket already reserved");
		}
		ticket.setReserver(user);
//		System.out.println(ticket.getReserver());
		ticket.setReservationTimeout(timeout);
//		System.out.println(ticket.getReservationTimeout());
		ticket.setBookingId(DigestUtils.md5DigestAsHex(String.format("%d %d %s %d", seat.getFlight().getFlightNumber(),
			seat.getRow(), seat.getSeat(), user.getId()).getBytes()));
		
		System.out.println(ticket.getBookingId());
		ticketDao.saveAndFlush(ticket);
		return ticket;
	}
	
	public List<Ticket> findByFlightNumber(int flightNumber) {
		List<Ticket> temp = ticketDao.findByFlight(flightNumber);
		return temp;
	}
	
	public Flight getFlight(final int flightNumber) {
		final List<Flight> list = flightDao.findByFlightNumber(flightNumber);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}
	
	public Ticket cancelExistingBooking(final Ticket ticket) {
		ticketDao.saveAndFlush(ticket);
		return ticket;
	}


	public List<Ticket> findByUserId(int id) {
		
		return ticketDao.findByUser(id);
	}
}
