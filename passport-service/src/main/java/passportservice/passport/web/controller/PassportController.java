package passportservice.passport.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import passportservice.passport.service.PassportService;
import passportservice.passport.web.dto.PassportDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passport")
public class PassportController {
    private final PassportService passportService;

    @PostMapping("/create-passport-data")
    public ResponseEntity<?> createPassportData(@Valid @RequestBody PassportDTO passportDTO, BindingResult bindingResult,
                                    @RequestHeader("Authorization") String authorizationHeader) {
        return passportService.createPassportData(passportDTO, bindingResult, authorizationHeader);
    }

    @GetMapping("/get-passport-data")
    public ResponseEntity<?> getPassportData(@RequestHeader("Authorization") String authorizationHeader) {
        return passportService.getPassportData(authorizationHeader);
    }
}
