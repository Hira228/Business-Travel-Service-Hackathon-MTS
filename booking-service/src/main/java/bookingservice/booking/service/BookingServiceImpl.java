package bookingservice.booking.service;


import bookingservice.booking.repository.ApprovedUserRepository;
import bookingservice.booking.repository.BookingRepository;
import bookingservice.booking.state.BookingState;
import bookingservice.booking.web.dto.BookingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ApprovedUserRepository approvedUserRepository;

    @Override
    public ResponseEntity<?> createBusinessTrip(BookingDTO bookingDTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> getBusinessTrip() {
        return null;
    }

    @Override
    public ResponseEntity<?> changeBookingStatus(UUID bookingId, BookingState bookingState) {
        return null;
    }

    @Override
    public ResponseEntity<?> getAllApprovedUsers() {
        return ResponseEntity.ok(approvedUserRepository.findAll());
    }
}
