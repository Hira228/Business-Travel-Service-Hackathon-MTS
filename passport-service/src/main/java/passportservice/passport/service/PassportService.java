package passportservice.passport.service;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import passportservice.passport.web.dto.PassportDTO;

public interface PassportService {

    ResponseEntity<?> createPassportData(PassportDTO passportDTO, BindingResult bindingResult, String token);

    ResponseEntity<?> getPassportData(String token);
}
