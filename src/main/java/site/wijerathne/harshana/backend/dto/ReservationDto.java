package site.wijerathne.harshana.backend.dto;

import lombok.Data;
import site.wijerathne.harshana.backend.enums.ReservationStatus;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Long price;
    private ReservationStatus status;
    private Long roomId;
    private  String roomType;
    private String roomName;
    private Long userId;
    private String userName;
}
