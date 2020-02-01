package com.team.alpha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.alpha.model.Flight;
import com.team.alpha.model.SeatAspects;
import com.team.alpha.model.Ticket;



@Repository
public interface TicketRepository extends JpaRepository<Ticket, SeatAspects> {
	
	@Query(value="from Ticket where flight=:flight and seat_row=:seatRow and seat=:seat")
	Ticket findByCompositeKeys(@Param("flight") Flight flightnumber, @Param("seatRow") int seatrow, 
			@Param("seat") String seat);
	
	@Query(value="SELECT * FROM tbl_tickets t WHERE t.flight=flight", nativeQuery = true)
	List<Ticket> findByFlight(int flight);
	
	@Query(value="SELECT * FROM tbl_tickets t WHERE t.reserver=reserver", nativeQuery = true)
	List<Ticket> findByUser(int reserver);
	
	List<Ticket> findByBookingId(String bookingId);
	
}
