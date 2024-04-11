package bookingservice.booking.web.dto;

import bookingservice.booking.entity.Flight;
import bookingservice.booking.entity.Hotel;
import bookingservice.booking.state.BookingState;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDTO {

    @JsonIgnore
    UUID id;

    @NotNull
    UUID userId;

    @NotNull
    LocalDate date;

    @NotNull
    Long days;


    Hotel hotel;


    Flight departureFlight;


    Flight arrivalFlight;

    @NotNull
    BookingState bookingState;

    Long price;
}
