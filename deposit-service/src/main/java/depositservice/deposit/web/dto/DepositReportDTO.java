package depositservice.deposit.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepositReportDTO {

    @JsonIgnore
    private UUID id;

    @JsonIgnore
    private UUID userId;

    @JsonIgnore
    private UUID depositId;

    @NotNull
    private Long totalReceived;

    @NotNull
    private Long spending;
}
