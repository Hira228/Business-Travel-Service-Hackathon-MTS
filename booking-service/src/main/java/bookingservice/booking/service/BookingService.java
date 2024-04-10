package bookingservice.booking.service;

import bookingservice.booking.state.BookingState;
import bookingservice.booking.web.dto.BookingDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BookingService {

    ResponseEntity<?> createBusinessTrip(BookingDTO bookingDTO); // admin

    ResponseEntity<?> getBusinessTrip();  // user

    ResponseEntity<?> changeBookingStatus(UUID bookingId, BookingState bookingState); // admin

    ResponseEntity<?> getAllApprovedUsers(); // admin


}
