package bookingservice.booking.config;

import bookingservice.booking.entity.Hotel;
import bookingservice.booking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HotelInitializer {
    private final HotelRepository hotelRepository;


    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        List<Hotel> hotels = Arrays.asList(
                new Hotel(UUID.randomUUID(), "Sunset Resort", 200L, "Miami"),
                new Hotel(UUID.randomUUID(), "Golden Sands Hotel", 150L, "Los Angeles"),
                new Hotel(UUID.randomUUID(), "Mountain View Lodge", 120L, "Aspen"),
                new Hotel(UUID.randomUUID(), "Ocean Breeze Inn", 180L, "San Francisco"),
                new Hotel(UUID.randomUUID(), "Lakeside Retreat", 170L, "Seattle"),
                new Hotel(UUID.randomUUID(), "City Lights Hotel", 160L, "New York"),
                new Hotel(UUID.randomUUID(), "Tranquil Haven", 190L, "Orlando"),
                new Hotel(UUID.randomUUID(), "Alpine Chalet", 210L, "Denver"),
                new Hotel(UUID.randomUUID(), "Harbor View Inn", 220L, "Boston"),
                new Hotel(UUID.randomUUID(), "Desert Oasis Resort", 230L, "Phoenix")

        );
        hotelRepository.saveAll(hotels);
    }
}