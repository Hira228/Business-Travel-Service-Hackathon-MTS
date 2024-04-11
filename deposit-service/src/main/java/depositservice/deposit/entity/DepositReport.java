package depositservice.deposit.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deposit_report")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepositReport {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    @OneToOne
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;

    @NotNull
    private Long totalReceived;

    @NotNull
    private Long spending;
}
