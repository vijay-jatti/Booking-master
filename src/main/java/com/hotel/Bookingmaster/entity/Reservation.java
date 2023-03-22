package com.hotel.Bookingmaster.entity;


import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class Reservation {

	@Size(min=2,message="The guest name is too small")
	private String guest;

	private double price;
	@FutureOrPresent(message="Booking Date should be current date or future dates")
	private LocalDate bookingDate;

	public int getNumberOfRooms() {
		return numberOfRooms;
	}

	public void setNumberOfRooms(int numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}

	private int numberOfRooms;

	private int stayDays;
	// reservation super and fields constructors

	public Reservation() {
	}

	public Reservation(String guest, double price, LocalDate arrivalDate, int stayDays, int numberOfRooms) {
		this.guest = guest;
		this.price = price;
		this.bookingDate = arrivalDate;
		this.stayDays = stayDays;
		this.numberOfRooms = numberOfRooms;
	}

	// reservation getters and setters fields


	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getBookingDate() {

		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getStayDays() {
		return stayDays;
	}

	public void setStayDays(int stayDays) {
		this.stayDays = stayDays;
	}
	
	// override to string method to contain all fields
	
	@Override
	public String toString() {
		return "Reservation " + "[guest=" + guest + ", price=" + price  + ", arrivalDate=" + bookingDate
				+ ", stayDays=" + stayDays + "]";
	}
}
