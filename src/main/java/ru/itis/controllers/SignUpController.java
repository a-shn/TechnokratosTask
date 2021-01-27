package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.dtos.UserDto;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.interfaces.SignUpService;

@Controller
@AllArgsConstructor
public class SignUpController {
    private final SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            return "redirect:/";
        }
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserDto userDto) {
        signUpService.signUp(userDto);
        return "redirect:/login";
    }
}
