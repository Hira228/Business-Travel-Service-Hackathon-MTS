package passportservice.passport.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PassportError {
    int status;
    String message;
    Date timestamp;

    public PassportError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
