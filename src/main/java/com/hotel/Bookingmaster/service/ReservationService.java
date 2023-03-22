package com.hotel.Bookingmaster.service;

import com.hotel.Bookingmaster.entity.Reservation;
import com.hotel.Bookingmaster.entity.Room;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

//Service Pattern for Reservation
public interface ReservationService {
	
	public Room getAvailableRoomsByDate(LocalDate date);

	public ArrayList<Reservation> getReservationsForGuest(String guest);
	
	public boolean saveReservation(Reservation currentReservation);

}
