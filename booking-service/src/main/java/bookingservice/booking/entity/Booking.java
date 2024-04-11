package bookingservice.booking.entity;

import bookingservice.booking.state.BookingState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @NotNull
    UUID userId;

    @NotNull
    LocalDate date;

    @NotNull
    Long days;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "departure_flight_id")
    Flight departureFlight;

    @ManyToOne
    @JoinColumn(name = "arrival_flight_id")
    Flight arrivalFlight;

    BookingState bookingState;

    Long price;
}