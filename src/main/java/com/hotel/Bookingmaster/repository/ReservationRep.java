package com.hotel.Bookingmaster.repository;

import com.hotel.Bookingmaster.entity.Reservation;
import com.hotel.Bookingmaster.entity.Room;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface ReservationRep {


	Collection<Reservation> getReservationsForGuest(String guest);
	public Room getAvailableRoomsByDate(LocalDate date);
	boolean save(Reservation reservation);

    void setRoomsCount(int i);

	/*
	* This method should delete the historical entries of past one month
	* Due to time constraint didnt add this method
	* */
	//Need to add a cleanup method to clean the rooms hashmap periodically by date.
}
