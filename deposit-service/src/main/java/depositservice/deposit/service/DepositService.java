package depositservice.deposit.service;

import depositservice.deposit.web.dto.DepositReportDTO;
import org.springframework.http.ResponseEntity;

public interface DepositService {

    ResponseEntity<?> getDepositCheck(String token);

    ResponseEntity<?> createDepositReport(DepositReportDTO depositReportDTO);
}
