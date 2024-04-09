package authservice.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users", indexes = {
        @Index(name = "username_index", columnList = "username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;

    @NotNull
    String username;

    @NotNull
    String password;

    Collection<String> roles;
}
