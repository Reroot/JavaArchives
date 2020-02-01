package com.team.alpha.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@Table(name="tbl_tickets")
public class Ticket {
	
	@EmbeddedId
	@JsonUnwrapped
	private SeatAspects id;
	
	@Column(name="class")
	private int seatClass;
	
	@ManyToOne
	@JoinColumn(nullable=true, name="reserver")
	@JsonProperty(access=Access.WRITE_ONLY)
	private User reserver;
	
	@Column(nullable=true, name="price")
	private Integer price;
	
	@Column(nullable=true, name="reservation_timeout")
	private LocalDateTime reservationTimeout;

	@Column(nullable=true, name="booking_id")
	private String bookingId;
	
	public Ticket() {	
	}
	
	public Ticket(final SeatAspects id, final int seatClass) {
		this.id = id;
		this.seatClass = seatClass;
	}

	public SeatAspects getId() {
		return id;
	}

	public int getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(int seatClass) {
		this.seatClass = seatClass;
	}

	public User getReserver() {
		return this.reserver;
	}

	public void setReserver(final User reserver) {
		if (reserver == null) {
			price = null;
			reservationTimeout = null;
			bookingId = null;
		}
		this.reserver = reserver;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(final Integer price) {
		this.price = price;
	}

	public LocalDateTime getReservationTimeout() {
		return reservationTimeout;
	}

	public void setReservationTimeout(final LocalDateTime reservationTimeout) {
		System.out.println("##################################################"+reservationTimeout);
//		if (this.reserver == null && reservationTimeout != null) {
//			throw new IllegalStateException("Tickets only expire if someone reserved them");
//		} else if (this.reserver != null && this.price != null && reservationTimeout != null) {
//			throw new IllegalStateException("Only unconfirmed bookings can time out");
//		}
		System.out.println("##################################################"+reservationTimeout);
		this.reservationTimeout = reservationTimeout;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId( final String bookingId) {
		if (reserver == null && bookingId != null) {
			throw new IllegalStateException("Unbooked seat cannot have a booking ID");
		} else if (reserver != null && bookingId == null) {
			throw new IllegalStateException("Booked seat must have booking ID");
		}
		
		this.bookingId = bookingId;
	}

//	@JsonIgnore
//	public boolean isValid() {
//		if (reserver == null) {
//			return reservationTimeout == null && price == null && bookingId == null;
//		} else if (bookingId == null) {
//			return false;
//		} else if (price == null) {
//			return reservationTimeout != null;
//		} else {
//			return reservationTimeout == null;
//		}
//	}
//	
//	@JsonGetter(value = "reserved")
//	public boolean isReserved() {
//		return reserver != null;
//	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", seatClass=" + seatClass + ", reserver=" + reserver + ", price=" + price
				+ ", reservationTimeout=" + reservationTimeout + ", bookingId=" + bookingId + "]";
	}
	
	
}
