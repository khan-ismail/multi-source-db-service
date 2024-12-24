package zerotoismail.com.datasourcelearningserviceorg.controller;

import org.springframework.web.bind.annotation.*;
import zerotoismail.com.datasourcelearningserviceorg.security.JwtProvider;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtProvider jwtProvider;

    public AuthController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public String login(@RequestParam String tenantId, @RequestParam String role) {
        return jwtProvider.generateJwtToken(tenantId, role);
    }
}