package com.hotel.Bookingmaster.ServiceTest;



import com.hotel.Bookingmaster.entity.Reservation;
import com.hotel.Bookingmaster.entity.Room;
import com.hotel.Bookingmaster.repository.ReservationRep;
import com.hotel.Bookingmaster.service.ReservationService;
import com.hotel.Bookingmaster.service.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {
    @Mock
    private ReservationRep reservationRepository;
    ReservationService reservationService;

    @BeforeEach
    void initUseCase() {
        reservationService = new ReservationServiceImpl(reservationRepository);
    }
    @Test
    public void savedrReservation_Success() {
        Reservation reservation=new Reservation("swara",1000.0, LocalDate.now(),1,1);
        // providing knowledge
        when(reservationRepository.save(any(Reservation.class))).thenReturn(true);
        boolean savedReservationStatus = reservationService.saveReservation(reservation);
        assertThat(savedReservationStatus).isEqualTo(true);
    }
    @Test
    public void savedrReservation_Failed() {
        Reservation reservation=new Reservation("swara",1000.0, LocalDate.now(),1,11);
        // providing knowledge
        when(reservationRepository.save(any(Reservation.class))).thenReturn(false);
        boolean savedReservationStatus = reservationService.saveReservation(reservation);
        assertThat(savedReservationStatus).isEqualTo(false);
    }

    @Test
    public void getReservationsForGuest_success() {
        Reservation reservation=new Reservation("swara",1000.0, LocalDate.now(),1,1);
        ArrayList<Reservation> sendReservations=new ArrayList<>();
        sendReservations.add(reservation);
        // providing knowledge
        when(reservationRepository.getReservationsForGuest("swara")).thenReturn(sendReservations);

        ArrayList<Reservation> reservations = reservationService.getReservationsForGuest("swara");
        assertThat(reservations.get(0)).isEqualTo(reservation);
    }

    @Test
    public void getReservationsForGuest_failed() {
        // providing knowledge
        when(reservationRepository.getReservationsForGuest("swara")).thenReturn(null);

        ArrayList<Reservation> reservations = reservationService.getReservationsForGuest("swara");
        assertThat(reservations).isEqualTo(null);
    }

    @Test
    public void getAvailableRoomsByDate_success() {
        // providing knowledge
        when(reservationRepository.getAvailableRoomsByDate(LocalDate.now())).thenReturn(new Room(5,LocalDate.now()));

        Room room = reservationService.getAvailableRoomsByDate(LocalDate.now());
        assertThat(room.getRoomsAvailable()).isEqualTo(5);
    }
}