package com.hotel.Bookingmaster.entity;

import java.util.ArrayList;
import java.util.Collection;


public class User {

	// User fields and annotate with it's column to connect to jpa entity manager
	

	private int id;


	private String username;
	private String password;
	private String email;

	private Collection<Reservation> reservations;

	// User super and fields constructors

	public User() {
	}

	public User(String userName, String password, String email, Collection<Reservation> reservations) {
		this.username = userName;
		this.password = password;
		this.email = email;
		this.reservations = reservations;
	}

	// User getters and setters fields

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}

	// override to string method to contain all fields

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + username + ", password=" + password + ", email=" + email + ", reservations=" + reservations + "]";
	}

}

