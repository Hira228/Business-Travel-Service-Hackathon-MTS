package authservice.auth.web.controller;


import authservice.auth.service.AuthenticationService;
import authservice.auth.web.dto.AuthenticationRequest;
import authservice.auth.web.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUP(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        return authenticationService.registerUser(userDTO, bindingResult);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {
        return authenticationService.authenticateUser(authenticationRequest, bindingResult);
    }

    @GetMapping("/validate")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<?> validateToken() {
        return ResponseEntity.ok().build();
    }

}