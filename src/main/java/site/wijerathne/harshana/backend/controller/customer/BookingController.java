package site.wijerathne.harshana.backend.controller.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.wijerathne.harshana.backend.dto.ReservationDto;
import site.wijerathne.harshana.backend.service.customer.booking.BookingService;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/book")
    public ResponseEntity<?> postBooking(@RequestBody ReservationDto reservationDto) {
        boolean success = bookingService.postReservation(reservationDto);
        if (success) {
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/booking/{userId}/{pageNumber}")
    public ResponseEntity<?> getAllBookingReservationByUser(@PathVariable Long userId, @PathVariable int pageNumber) {
        try {
            return ResponseEntity.ok(bookingService.getAllReservationByUser(userId, pageNumber));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went Wrong");
        }
    }
}
