package site.wijerathne.harshana.backend.service.customer.booking;

import site.wijerathne.harshana.backend.dto.ReservationDto;
import site.wijerathne.harshana.backend.dto.ReservationResponseDto;

public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
    ReservationResponseDto getAllReservationByUser(Long userId, int pageNumber);
}
