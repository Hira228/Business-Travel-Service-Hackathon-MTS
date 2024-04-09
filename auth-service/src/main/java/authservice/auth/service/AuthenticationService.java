package authservice.auth.service;


import authservice.auth.web.dto.AuthenticationRequest;
import authservice.auth.web.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface AuthenticationService {
    ResponseEntity<?> registerUser(UserDTO userDTO, BindingResult bindingResult);

    ResponseEntity<?> authenticateUser(AuthenticationRequest authenticationRequest, BindingResult bindingResult);

}