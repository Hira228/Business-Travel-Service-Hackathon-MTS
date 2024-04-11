package depositservice.deposit.web.controller;

import depositservice.deposit.service.DepositService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deposit")
public class DepositController {
    private final DepositService depositService;

    @GetMapping("/get-deposit-check")
    public ResponseEntity<?> getDepositCheck(){
        return null;
    }
}
