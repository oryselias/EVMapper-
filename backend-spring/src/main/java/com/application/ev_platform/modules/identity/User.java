package com.application.ev_platform.modules.identity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "users")   // 👈 IMPORTANT
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String role;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    // getters & setters
}
