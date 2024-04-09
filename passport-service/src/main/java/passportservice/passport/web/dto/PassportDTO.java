package passportservice.passport.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class PassportDTO {

    @JsonIgnore
    UUID id;

    @JsonIgnore
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
