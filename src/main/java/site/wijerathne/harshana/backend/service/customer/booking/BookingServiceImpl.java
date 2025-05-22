package site.wijerathne.harshana.backend.service.customer.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.wijerathne.harshana.backend.dto.ReservationDto;
import site.wijerathne.harshana.backend.entity.Reservation;
import site.wijerathne.harshana.backend.entity.Room;
import site.wijerathne.harshana.backend.entity.User;
import site.wijerathne.harshana.backend.enums.ReservationStatus;
import site.wijerathne.harshana.backend.repository.ReservationRepository;
import site.wijerathne.harshana.backend.repository.RoomRepository;
import site.wijerathne.harshana.backend.repository.UserRepository;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    public boolean postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

        if(optionalUser.isPresent() && optionalRoom.isPresent()) {
            User user = optionalUser.get();
            Room room = optionalRoom.get();

            Reservation reservation = new Reservation();
            reservation.setRoom(room);
            reservation.setUser(user);
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservation.getCheckInDate(), reservation.getCheckOutDate());
            reservation.setPrice(days * reservationDto.getPrice());
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
}
