package com.team.alpha.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SeatAspects implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="flight")
	private Flight flight;
	
	@Column(name="seat_row")
    private int row;
	
	@Column(name="seat")
    private String seat;
	
	public SeatAspects() {
		
	}

	public SeatAspects(final Flight flight, final int row, final String seat) {
		this.flight = flight;
		this.row = row;
		this.seat = seat;
	}

	public Flight getFlight() {
		return flight;
	}

	public int getRow() {
		return row;
	}

	public String getSeat() {
		return seat;
	}
	
	

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flight, row, seat);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof SeatAspects) {
			return Objects.equals(flight, ((SeatAspects) obj).getFlight())
					&& row == ((SeatAspects) obj).getRow()
					&& Objects.equals(seat, ((SeatAspects) obj).getSeat());
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "SeatAspects [flight=" + flight + ", row=" + row + ", seat=" + seat + "]";
	}
	
	
}
