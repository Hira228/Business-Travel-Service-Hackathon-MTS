package bookingservice.booking.web.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApprovedUserDTO {

    @NotNull
    UUID id;

    @NotNull
    UUID userId;

    @NotNull
    Long passportNumber;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    LocalDate dateOfBirth;

}
