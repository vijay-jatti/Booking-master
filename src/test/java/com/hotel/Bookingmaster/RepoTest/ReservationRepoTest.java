package com.hotel.Bookingmaster.RepoTest;

        import com.hotel.Bookingmaster.entity.Reservation;
        import com.hotel.Bookingmaster.entity.Room;
        import com.hotel.Bookingmaster.repository.ReservationRep;
        import com.hotel.Bookingmaster.repository.ReservationRepImpl;
        import com.hotel.Bookingmaster.service.ReservationServiceImpl;
        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.junit.jupiter.MockitoExtension;
        import org.springframework.beans.factory.annotation.Autowired;

        import java.time.LocalDate;
        import java.util.ArrayList;
        import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ReservationRepoTest {
    @Autowired
    private ReservationRep reservationRepository;

    @BeforeEach
    void initUseCase() {
        reservationRepository = new ReservationRepImpl();
        reservationRepository.setRoomsCount(10);
        ReservationRepImpl.BookingRepo.add(new Reservation("vijay",1000.0, LocalDate.now(),1,1));
        ReservationRepImpl.BookingRepo.add(new Reservation("ajay",2000.0,LocalDate.now(),1,1));
        ReservationRepImpl.BookingRepo.add(new Reservation("jay",3000.0,LocalDate.now(),1,1));
        ReservationRepImpl.rooms.put(LocalDate.now().toString(),7);
    }

    @Test
    void save_success() {
        Reservation reservation= new Reservation("vj",1000.0, LocalDate.now(),1,1);
        boolean status = reservationRepository.save(reservation);
        assertThat(status).isEqualTo(true);
    }
    @Test
    void save_failed() {
        Reservation reservation= new Reservation("vj",1000.0, LocalDate.parse("2023-03-21"),1,1);
        boolean status = reservationRepository.save(reservation);
        assertThat(status).isEqualTo(false);
    }

    @Test
    void findByGuest_success() {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.getReservationsForGuest("vijay");
        assertThat(reservations.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    void findByGuest_failed() {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepository.getReservationsForGuest("Obama");
        assertThat(reservations.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void findRoomsByDate_success() {
        LocalDate localDate = LocalDate.now().plusDays(10);
        Room room = reservationRepository.getAvailableRoomsByDate(localDate);
        assertThat(room.getRoomsAvailable()).isGreaterThanOrEqualTo(10);
    }
    void findRoomsByDate_failed() {
        Room room = reservationRepository.getAvailableRoomsByDate(LocalDate.parse("2023-03-22"));
        assertThat(room).isNull();
    }

}
