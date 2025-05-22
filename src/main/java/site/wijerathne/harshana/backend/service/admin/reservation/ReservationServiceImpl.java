package site.wijerathne.harshana.backend.service.admin.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.wijerathne.harshana.backend.dto.ReservationResponseDto;
import site.wijerathne.harshana.backend.entity.Reservation;
import site.wijerathne.harshana.backend.repository.ReservationRepository;

import javax.swing.plaf.PanelUI;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public static final int SEARCH_RESULT_PER_PAGE = 4;

    public ReservationResponseDto getAllReservations(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);

        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));

        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());
        return reservationResponseDto;
    }
}
