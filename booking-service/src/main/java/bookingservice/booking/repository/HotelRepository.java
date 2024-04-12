package bookingservice.booking.repository;

import bookingservice.booking.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    List<Hotel> findAllByCity(String city);

    @Query("SELECT DISTINCT h.city FROM Hotel h")
    List<String> findAllCities();
}
