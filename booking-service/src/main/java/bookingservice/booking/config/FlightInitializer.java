package bookingservice.booking.config;



import bookingservice.booking.entity.Flight;
import bookingservice.booking.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class FlightInitializer {
    private final FlightRepository flightRepository;

    List<String> cities = Arrays.asList(
            "Miami", "Los Angeles", "Aspen", "San Francisco", "Seattle",
            "New York", "Orlando", "Denver", "Boston", "Phoenix"
    );

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        List<Flight> flights = new ArrayList<>();

        LocalDate startDate = LocalDate.now().plusMonths(1);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                String departureCity = cities.get(i);
                String arrivalCity = cities.get(j);

                long price = random.nextInt(300) + 100;

                LocalDate randomDate = startDate.plusDays(random.nextInt(7));

                Flight flight = new Flight(UUID.randomUUID(), randomDate, price, departureCity, arrivalCity);
                flights.add(flight);
            }
        }
        flightRepository.saveAll(flights);
    }
}

