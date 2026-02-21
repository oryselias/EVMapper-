package com.application.ev_platform.modules.identity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // GET all users
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repo.findAll();
    }

    // GET user by ID
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    // GET user by email
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    // POST - Register new user
    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {
        // Check if email exists
        if (repo.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        
        if (request.getRole() != null) {
            try {
                user.setRole(User.UserRole.valueOf(request.getRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                user.setRole(User.UserRole.DRIVER);
            }
        } else {
            user.setRole(User.UserRole.DRIVER);
        }
        
        user.setActive(true);
        
        User saved = repo.save(user);
        
        // Generate JWT token
        String token = jwtService.generateToken(saved);
        
        return new AuthResponseDTO(token, saved.getId(), saved.getEmail(), saved.getName(), saved.getRole().name());
    }

    // POST - Login
    @Transactional(readOnly = true)
    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        
        if (!user.getActive()) {
            throw new RuntimeException("Account is disabled");
        }
        
        String token = jwtService.generateToken(user);
        
        return new AuthResponseDTO(token, user.getId(), user.getEmail(), user.getName(), user.getRole().name());
    }

    // PUT - Update user
    @Transactional
    public User updateUser(Long id, RegisterRequestDTO request) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (request.getName() != null) user.setName(request.getName());
        if (request.getPhone() != null) user.setPhone(request.getPhone());
        
        return repo.save(user);
    }

    // DELETE - Deactivate user
    @Transactional
    public void deactivateUser(Long id) {
        User user = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setActive(false);
        repo.save(user);
    }
}
