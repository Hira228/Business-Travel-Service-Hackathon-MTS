package depositservice.deposit.service;

import depositservice.deposit.entity.Deposit;
import depositservice.deposit.entity.DepositReport;
import depositservice.deposit.repository.DepositReportRepository;
import depositservice.deposit.repository.DepositRepository;
import depositservice.deposit.web.dto.DepositError;
import depositservice.deposit.web.dto.DepositReportDTO;
import depositservice.deposit.web.mapper.DepositReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Response;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService{
    private final DepositRepository depositRepository;
    private final DepositReportRepository depositReportRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final DepositReportMapper depositReportMapper;

    @Override
    public ResponseEntity<?> getDepositCheck(String token) {
        Optional<Deposit> deposit = depositRepository.findByUserId(UUID.fromString(redisTemplate.opsForValue().get(token.substring(7)).toString()));
        if (deposit.isEmpty())
            return new ResponseEntity<>(new DepositError(HttpStatus.BAD_REQUEST.value(), "Your deposit check is not ready yet."), HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(depositRepository.findByUserId(UUID.fromString(redisTemplate.opsForValue().get(token.substring(7)).toString())).get());
    }

    @Override
    public ResponseEntity<?> createDepositReport(DepositReportDTO depositReportDTO) {
        return null;
    }
}
