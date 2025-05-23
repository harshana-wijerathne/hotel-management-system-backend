package site.wijerathne.harshana.backend.service.admin.reservation;

import site.wijerathne.harshana.backend.dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto getAllReservations(int pageNumber);
    boolean changeReservationStatus(Long id , String status);
}
