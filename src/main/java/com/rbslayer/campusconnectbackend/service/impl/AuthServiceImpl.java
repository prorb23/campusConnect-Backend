package com.rbslayer.campusconnectbackend.service.impl;

import com.rbslayer.campusconnectbackend.config.JwtService;
import com.rbslayer.campusconnectbackend.dto.auth.request.LoginRequest;
import com.rbslayer.campusconnectbackend.dto.auth.request.RegisterRequest;
import com.rbslayer.campusconnectbackend.dto.auth.response.AuthResponse;
import com.rbslayer.campusconnectbackend.entity.Role;
import com.rbslayer.campusconnectbackend.entity.User;
import com.rbslayer.campusconnectbackend.repository.UserRepository;
import com.rbslayer.campusconnectbackend.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword((passwordEncoder.encode(request.getPassword())));
        user.setRole(Role.ROLE_STUDENT);

        userRepository.save(user);
        return new AuthResponse("User Registered Successfully");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + request.getEmail()
                        )
                );
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
