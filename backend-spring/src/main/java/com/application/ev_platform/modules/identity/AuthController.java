package com.application.ev_platform.modules.identity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/auth/register - Register new user
    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return userService.register(request);
    }

    // POST /api/auth/login - Login user
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO request) {
        return userService.login(request);
    }

    // GET /api/auth/me - Get current user (requires authentication)
    // This endpoint requires authentication - Spring Security will handle it
}
