package authservice.auth.exceptions;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthError {
    int status;
    String message;
    Date timestamp;

    public AuthError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
