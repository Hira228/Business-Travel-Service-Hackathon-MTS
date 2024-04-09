package passportservice.passport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "passports")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    UUID userId;

    @Column(unique = true)
    Long passportNumber;

    @NotNull
    @Size(max = 20)
    String firstName;

    @NotNull
    @Size(max = 20)
    String lastName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth;
}
