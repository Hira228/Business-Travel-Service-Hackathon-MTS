package depositservice.deposit.repository;

import depositservice.deposit.entity.DepositReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepositReportRepository extends JpaRepository<DepositReport, UUID> {
}
