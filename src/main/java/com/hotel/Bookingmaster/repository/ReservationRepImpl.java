package com.hotel.Bookingmaster.repository;

import com.hotel.Bookingmaster.entity.Reservation;
import com.hotel.Bookingmaster.entity.Room;
import com.hotel.Bookingmaster.exception.RoomsNotAvailableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReservationRepImpl implements ReservationRep{

    private static List<Reservation> BookingRepo = new ArrayList<Reservation>();
    private static HashMap<String,Integer> rooms = new HashMap<>();

    private static int roomsCount;
    @Value("${booking.rooms.limit}")
    public void setRoomsCount(int limit){
        ReservationRepImpl.roomsCount = limit;
    }
    static {
        BookingRepo.add(new Reservation("vijay",1000.0,LocalDate.now(),1,1));
        BookingRepo.add(new Reservation("ajay",2000.0,LocalDate.now(),1,1));
        BookingRepo.add(new Reservation("jay",3000.0,LocalDate.now(),1,1));
        System.out.println(LocalDate.now().toString());
        rooms.put(LocalDate.now().toString(),7);
    }
    @Override
    public ArrayList<Reservation> getReservationsForGuest(String guest) {
        return (ArrayList<Reservation>) BookingRepo
                .stream()
                .filter(reservation -> reservation.getGuest().equalsIgnoreCase(guest))
                .collect(Collectors.toList());
    }

    @Override
    public Room getAvailableRoomsByDate(LocalDate date) {
        if(!date.isBefore(LocalDate.now())){
            return new Room(rooms.getOrDefault(date.toString(), roomsCount), date);
        }
        return null;
    }

    @Override
    public boolean save(Reservation reservation) {
        if(getRoomAvailablity(reservation)){
            return BookRoom(reservation);
        }
        return false;
    }

    public boolean getRoomAvailablity(Reservation reservation){
        int i=0;
        int j=0;
        LocalDate startDate =reservation.getBookingDate();
        int stayDays = reservation.getStayDays();
        if(rooms==null){
            return true;
        }
        while(i<stayDays) {
            startDate.plusDays(i);
            if (!rooms.containsKey(startDate.toString()) || rooms.get(startDate.toString())>0) {
               j++;
            }
            i++;
        }
        System.out.println("i="+i);
        System.out.println("j="+j);
        if(i==j) {
            return true;
        }
        return false;
    }

    public boolean BookRoom(Reservation reservation){
        int i=0;
        LocalDate startDate =reservation.getBookingDate();
        if(reservation.getBookingDate().isBefore(LocalDate.now())){
            return false;
        }
        int stayDays = reservation.getStayDays();
        int numberOfRooms = reservation.getNumberOfRooms();
        HashMap<String,Integer> tempRooms= new HashMap();
        int roomsCntTemp=roomsCount;
        while(i<stayDays) {
            startDate=startDate.plusDays(i);
            if (!rooms.containsKey(startDate.toString()) || rooms.get(startDate.toString())>0) {
                if(roomsCntTemp-numberOfRooms>=0) {
                    roomsCntTemp = rooms.containsKey(startDate.toString()) ? rooms.get(startDate.toString()) - numberOfRooms
                            : roomsCntTemp - numberOfRooms;
                    tempRooms.put(startDate.toString(), roomsCntTemp);
                }
                else{
                    return false;
                }
            }
            i++;
            roomsCntTemp=roomsCount;
        }
        if(i==tempRooms.size()){
            rooms.putAll(tempRooms);
            BookingRepo.add(reservation);
            return true;
        }
        return false;
    }
}
