package site.wijerathne.harshana.backend.service.customer.booking;

import site.wijerathne.harshana.backend.dto.ReservationDto;

public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
}
