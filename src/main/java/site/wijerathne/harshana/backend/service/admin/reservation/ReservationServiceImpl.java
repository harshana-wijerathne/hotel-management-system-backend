package site.wijerathne.harshana.backend.service.admin.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.wijerathne.harshana.backend.dto.ReservationResponseDto;
import site.wijerathne.harshana.backend.entity.Reservation;
import site.wijerathne.harshana.backend.entity.Room;
import site.wijerathne.harshana.backend.enums.ReservationStatus;
import site.wijerathne.harshana.backend.repository.ReservationRepository;
import site.wijerathne.harshana.backend.repository.RoomRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.swing.plaf.PanelUI;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final SimpMessagingTemplate messagingTemplate;

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

    public boolean changeReservationStatus(Long id , String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent() ){
            Reservation existingReservation = optionalReservation.get();
            if(Objects.equals(status,"Approve")){
                existingReservation.setStatus(ReservationStatus.APPROVED);
            }else{
                existingReservation.setStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(existingReservation);

            Room existingRoom = existingReservation.getRoom();
            existingRoom.setAvailable(false);

            roomRepository.save(existingRoom);

            // Broadcast the updated reservation to all subscribers
            messagingTemplate.convertAndSend(
                    "/topic/reservations",
                    existingReservation.getReservationDto()
            );
            return true;
        }
        return false;
    }
}
