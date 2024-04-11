package bookingservice.booking.service;

import bookingservice.booking.entity.Booking;
import bookingservice.booking.entity.Flight;
import bookingservice.booking.state.BookingState;
import bookingservice.booking.web.dto.BookingDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface BookingService {

    ResponseEntity<?> createBusinessTrip(BookingDTO bookingDTO,  BindingResult bindingResult); // admin

    ResponseEntity<?> getAllApprovedUsers(); // admin

    ResponseEntity<?> acceptBookingAdmin(BookingDTO bookingDTO, BindingResult bindingResult); // admin

    ResponseEntity<?> rejectBookingAdmin(BookingDTO bookingDTO, BindingResult bindingResult); // admin

    ResponseEntity<List<Booking>> getMyBookings(String token); // user

    ResponseEntity<?> acceptBooking(String token, BookingDTO bookingDTO, BindingResult bindingResult); // user

    ResponseEntity<?> cancelBooking(String token, BookingDTO bookingDTO, BindingResult bindingResult); // user

    ResponseEntity<List<Flight>> getAllFlightInAtoB(String nameA, String nameB); // user

}
