package passportservice.passport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import passportservice.passport.entity.Passport;
import passportservice.passport.repository.PassportRepository;
import passportservice.passport.web.dto.PassportDTO;
import passportservice.passport.web.dto.PassportError;
import passportservice.passport.web.mapper.PassportMapper;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService{
    private final PassportRepository passportRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final PassportMapper passportMapper;
    @Override
    public ResponseEntity<?> createPassportData(PassportDTO passportDTO, BindingResult bindingResult,
                                                String token) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new PassportError(HttpStatus.BAD_REQUEST.value(), "Please fill in all fields for registration."), HttpStatus.BAD_REQUEST);
        }
        System.out.println(token.substring(7));
        Object id = redisTemplate.opsForValue().get(token.substring(7));
        System.out.println(id.toString());
        if(id == null) return new ResponseEntity<>(new PassportError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);

        if (passportRepository.existsByPassportNumber(passportDTO.getPassportNumber())) {
            return new ResponseEntity<>(new PassportError(HttpStatus.BAD_REQUEST.value(), "Passport with the given number already exists."), HttpStatus.BAD_REQUEST);
        }

        if (passportRepository.existsByUserId(UUID.fromString(id.toString()))) {
            return new ResponseEntity<>(new PassportError(HttpStatus.BAD_REQUEST.value(), "You already have your passport information filled out."), HttpStatus.BAD_REQUEST);
        }

        Passport passport = passportMapper.toEntity(passportDTO);
        passport.setUserId(UUID.fromString(id.toString()));
        passportRepository.save(passport);

        return new ResponseEntity<>(new PassportError(HttpStatus.OK.value(), "The passport data was successfully created."), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getPassportData(String token) {
        Optional<Passport> passport = passportRepository.findByUserId(UUID.fromString(redisTemplate.opsForValue().get(token.substring(7)).toString()));

        if(passport.isEmpty())return new ResponseEntity<>(new PassportError(HttpStatus.BAD_REQUEST.value(), "Your passport data has not been uploaded."), HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(passportRepository.findByUserId(UUID.fromString(redisTemplate.opsForValue().get(token.substring(7)).toString())).get());
    }
}
