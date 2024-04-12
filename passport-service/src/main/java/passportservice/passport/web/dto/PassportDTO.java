package passportservice.passport.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
