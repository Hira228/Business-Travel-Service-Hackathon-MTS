package authservice.auth.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    @JsonIgnore
    UUID id;

    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String confirmPassword;

    @JsonIgnore
    Collection<String> roles;
}
