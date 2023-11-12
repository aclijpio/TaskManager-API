package ru.pio.aclij.taskmanagerapi.security;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pio.aclij.taskmanagerapi.security.dtos.JwtRequest;
import ru.pio.aclij.taskmanagerapi.security.dtos.RegistrationUserDto;
import ru.pio.aclij.taskmanagerapi.security.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@ModelAttribute JwtRequest request, HttpServletResponse response) {
        return authService.AuthorizationToken(request, response);
    }
    @PostMapping("/register")
    public ResponseEntity<?> createNewUser(@ModelAttribute RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}