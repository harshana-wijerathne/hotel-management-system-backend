package site.wijerathne.harshana.backend.service.customer.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.wijerathne.harshana.backend.dto.ReservationDto;
import site.wijerathne.harshana.backend.dto.ReservationResponseDto;
import site.wijerathne.harshana.backend.entity.Reservation;
import site.wijerathne.harshana.backend.entity.Room;
import site.wijerathne.harshana.backend.entity.User;
import site.wijerathne.harshana.backend.enums.ReservationStatus;
import site.wijerathne.harshana.backend.repository.ReservationRepository;
import site.wijerathne.harshana.backend.repository.RoomRepository;
import site.wijerathne.harshana.backend.repository.UserRepository;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final static int SEARCH_RESULT_PER_PAGE = 4;

    public boolean postReservation(ReservationDto reservationDto) {

        System.out.println(reservationDto);

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

    public ReservationResponseDto getAllReservationByUser(Long userId,int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Reservation> reservationPage = reservationRepository.findAllByUserId(pageable , userId);

        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));

        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponseDto;
    }
}
