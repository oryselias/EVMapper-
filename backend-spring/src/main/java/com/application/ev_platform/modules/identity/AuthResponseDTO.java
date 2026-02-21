package com.application.ev_platform.modules.identity;

public class AuthResponseDTO {

    private String token;
    private String type = "Bearer";
    private Long userId;
    private String email;
    private String name;
    private String role;

    public AuthResponseDTO(String token, Long userId, String email, String name, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    // Getters
    public String getToken() { return token; }
    public String getType() { return type; }
    public Long getUserId() { return userId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getRole() { return role; }
}
