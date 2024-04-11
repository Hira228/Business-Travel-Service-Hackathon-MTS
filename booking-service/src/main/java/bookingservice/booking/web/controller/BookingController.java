package bookingservice.booking.web.controller;

import bookingservice.booking.entity.Booking;
import bookingservice.booking.entity.Flight;
import bookingservice.booking.entity.Hotel;
import bookingservice.booking.repository.HotelRepository;
import bookingservice.booking.service.BookingService;
import bookingservice.booking.web.dto.BookingDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final HotelRepository hotelRepository;

    @GetMapping("/admin/get-all-approved-users")
    public ResponseEntity<?> getAllApprovedUsers() {
        return bookingService.getAllApprovedUsers();
    }

    @PostMapping("/admin/create-booking")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingDTO bookingDTO,  BindingResult bindingResult) {
        return bookingService.createBusinessTrip(bookingDTO, bindingResult);
    }

    @GetMapping("/get-all-hotels-in-city/{city}")
    public ResponseEntity<List<Hotel>> getAllHotelsInCity(@PathVariable String city) {
        return ResponseEntity.ok(hotelRepository.findAllByCity(city));
    }

    @PostMapping("/accept-booking")
    public ResponseEntity<?> acceptBooking(@Valid @RequestBody BookingDTO bookingDTO, BindingResult bindingResult,
                                           @RequestHeader("Authorization") String authorizationHeader) {
        return bookingService.acceptBooking(authorizationHeader, bookingDTO, bindingResult);
    }

    @PostMapping("/cancel-booking")
    public ResponseEntity<?> cancelBooking(@Valid @RequestBody BookingDTO bookingDTO, BindingResult bindingResult,
                                           @RequestHeader("Authorization") String authorizationHeader) {
        return bookingService.cancelBooking(authorizationHeader, bookingDTO, bindingResult);
    }

    @GetMapping("/get-all-cities-with-hotels")
    public ResponseEntity<List<String>> getAllCitiesWithHotels() {
        return ResponseEntity.ok(hotelRepository.findAllCities());
    }

    @PostMapping("/admin/accept-booking")
    public ResponseEntity<?> acceptBookingAdmin(@Valid @RequestBody BookingDTO bookingDTO, BindingResult bindingResult) {
        return bookingService.acceptBookingAdmin(bookingDTO, bindingResult);
    }

    @PostMapping("/admin/reject-booking")
    public ResponseEntity<?> rejectBookingAdmin(@Valid @RequestBody BookingDTO bookingDTO, BindingResult bindingResult) {
        return bookingService.rejectBookingAdmin(bookingDTO, bindingResult);
    }

    @GetMapping("/get-my-booking")
    public ResponseEntity<List<Booking>> getMyBookings(@RequestHeader("Authorization") String authorizationHeader) {
        return bookingService.getMyBookings(authorizationHeader);
    }

    @GetMapping("/get-flights-in-a-to-b/{nameA}/{nameB}")
    public ResponseEntity<List<Flight>> getAllFlightInAtoB(@PathVariable String nameA,
                                                           @PathVariable String nameB) {
        return bookingService.getAllFlightInAtoB(nameA, nameB);
    }

}
