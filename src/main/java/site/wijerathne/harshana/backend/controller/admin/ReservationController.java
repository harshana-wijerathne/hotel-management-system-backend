package site.wijerathne.harshana.backend.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.wijerathne.harshana.backend.service.admin.reservation.ReservationService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/reservation/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber){
        try{
            return ResponseEntity.ok(reservationService.getAllReservations(pageNumber));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went Wrong");
        }
    }
}
