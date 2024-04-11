package bookingservice.booking.repository;

import bookingservice.booking.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
    List<Flight> findAllByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);
}
