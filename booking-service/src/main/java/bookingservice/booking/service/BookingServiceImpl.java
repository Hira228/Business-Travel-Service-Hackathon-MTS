package bookingservice.booking.service;


import bookingservice.booking.entity.Booking;
import bookingservice.booking.entity.Flight;
import bookingservice.booking.entity.Hotel;
import bookingservice.booking.exceptions.BookingError;
import bookingservice.booking.repository.ApprovedUserRepository;
import bookingservice.booking.repository.BookingRepository;
import bookingservice.booking.repository.FlightRepository;
import bookingservice.booking.repository.HotelRepository;
import bookingservice.booking.state.BookingState;
import bookingservice.booking.web.dto.BookingDTO;
import bookingservice.booking.web.mapper.BookingMapper;
import bookingservice.booking.web.mapper.Mappable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ApprovedUserRepository approvedUserRepository;
    private final BookingMapper bookingMapper;
    private final HotelRepository hotelRepository;
    private final FlightRepository flightRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public ResponseEntity<?> createBusinessTrip(BookingDTO bookingDTO,  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new BookingError(HttpStatus.BAD_REQUEST.value(), "Please fill in all fields for booking."), HttpStatus.BAD_REQUEST);
        }

        bookingDTO.setBookingState(BookingState.CREATED);

        bookingRepository.save(bookingMapper.toEntity(bookingDTO));

        return new ResponseEntity<>(new BookingError(HttpStatus.CREATED.value(), "Booking successfully created."), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getAllApprovedUsers() {
        return ResponseEntity.ok(approvedUserRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Booking>> getMyBookings(String token) {
        Object id = redisTemplate.opsForValue().get(token.substring(7));

        List<Booking> bookings = bookingRepository.findByUserId(UUID.fromString(Objects.requireNonNull(id).toString()));

        return ResponseEntity.ok(bookings);
    }

    @Override
    public ResponseEntity<?> acceptBooking(String token, BookingDTO bookingDTO, BindingResult bindingResult) {
        System.out.println(bookingDTO);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new BookingError(HttpStatus.BAD_REQUEST.value(), "Please fill in all fields for booking."), HttpStatus.BAD_REQUEST);
        }

        if (bookingDTO.getHotel() == null || bookingDTO.getArrivalFlight() == null || bookingDTO.getDepartureFlight() == null) {
            return new ResponseEntity<>(new BookingError(HttpStatus.BAD_REQUEST.value(), "Please fill in all fields for booking."), HttpStatus.BAD_REQUEST);
        }

        UUID userIdFromToken = UUID.fromString(Objects.requireNonNull(redisTemplate.opsForValue().get(token.substring(7))).toString());
        if (!userIdFromToken.equals(bookingDTO.getUserId())) {
            return new ResponseEntity<>(new BookingError(HttpStatus.NOT_FOUND.value(), "Booking not found."), HttpStatus.NOT_FOUND);
        }

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingDTO.getId());
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            Hotel hotel = hotelRepository.findById(bookingDTO.getHotel().getId()).orElse(null);
            Flight arrivalFlight = flightRepository.findById(bookingDTO.getArrivalFlight().getId()).orElse(null);
            Flight departureFlight = flightRepository.findById(bookingDTO.getDepartureFlight().getId()).orElse(null);

            if (hotel == null || arrivalFlight == null || departureFlight == null) {
                return new ResponseEntity<>(new BookingError(HttpStatus.NOT_FOUND.value(), "Hotel or flight not found."), HttpStatus.NOT_FOUND);
            }

            booking.setHotel(hotel);
            booking.setArrivalFlight(arrivalFlight);
            booking.setDepartureFlight(departureFlight);
            booking.setBookingState(BookingState.PENDING_APPROVAL);

            long totalPrice = hotel.getPrice() + arrivalFlight.getPrice() + departureFlight.getPrice();
            booking.setPrice(totalPrice);

            bookingRepository.save(booking);

            return ResponseEntity.ok("Booking accepted successfully. Expect a response from management.");
        } else {
            return new ResponseEntity<>(new BookingError(HttpStatus.NOT_FOUND.value(), "Booking not found."), HttpStatus.NOT_FOUND);
        }
    }


    @Override
    public ResponseEntity<?> cancelBooking(String token, BookingDTO bookingDTO, BindingResult bindingResult) {
        System.out.println(bookingDTO);

        UUID userIdFromToken = UUID.fromString(Objects.requireNonNull(redisTemplate.opsForValue().get(token.substring(7))).toString());
        if (!userIdFromToken.equals(bookingDTO.getUserId())) {
            return new ResponseEntity<>(new BookingError(HttpStatus.NOT_FOUND.value(), "Booking not found."), HttpStatus.NOT_FOUND);
        }

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingDTO.getId());

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            booking.setBookingState(BookingState.CANCELLED);

            bookingRepository.save(booking);

            return ResponseEntity.ok("Booking cancelled successfully.");
        } else {
            return new ResponseEntity<>(new BookingError(HttpStatus.NOT_FOUND.value(), "Booking not found."), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Flight>> getAllFlightInAtoB(String nameA, String nameB) {
        return ResponseEntity.ok(flightRepository.findAllByDepartureCityAndArrivalCity(nameA, nameB));
    }

    @Override
    public ResponseEntity<?> acceptBookingAdmin(BookingDTO bookingDTO, BindingResult bindingResult) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingDTO.getId());

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            booking.setBookingState(BookingState.APPROVED);

            bookingRepository.save(booking);

            return ResponseEntity.ok("Booking accepted successfully.");
        } else {
            return new ResponseEntity<>(new BookingError(HttpStatus.NOT_FOUND.value(), "Booking not found."), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> rejectBookingAdmin(BookingDTO bookingDTO, BindingResult bindingResult) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingDTO.getId());

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();

            booking.setBookingState(BookingState.REJECTED);

            bookingRepository.save(booking);

            return ResponseEntity.ok("Booking rejected successfully.");
        } else {
            return new ResponseEntity<>(new BookingError(HttpStatus.NOT_FOUND.value(), "Booking not found."), HttpStatus.NOT_FOUND);
        }
    }
}

