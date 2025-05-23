package site.wijerathne.harshana.backend.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.wijerathne.harshana.backend.service.admin.reservation.ReservationService;

@Slf4j
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

    @PostMapping("/reservation/{id}/{status}")
    public ResponseEntity<?> changeReservationStatus(@PathVariable Long id,@PathVariable String status){
        boolean success = reservationService.changeReservationStatus(id, status);
        if (success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong");
        }
    }
}
