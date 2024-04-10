package bookingservice.booking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hotel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "departure_flight_id")
    Flight departureFlight;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "arrival_flight_id")
    Flight arrivalFlight;
}
