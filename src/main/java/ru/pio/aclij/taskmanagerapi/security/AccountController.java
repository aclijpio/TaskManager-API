package ru.pio.aclij.taskmanagerapi.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.pio.aclij.taskmanagerapi.security.dtos.JwtRequest;
import ru.pio.aclij.taskmanagerapi.security.dtos.RegistrationUserDto;

@Controller
public class AccountController {
    @GetMapping("/registration")
    public String register(@ModelAttribute("registrationDto")RegistrationUserDto registrationUserDto){
        return "/authorization/registration";
    }
    @GetMapping("/login")
    public String login(@ModelAttribute("request") JwtRequest jwtRequest){
        return "/authorization/login";
    }
}
