package com.rbslayer.campusconnectbackend.service.impl;

import com.rbslayer.campusconnectbackend.dto.auth.request.LoginRequest;
import com.rbslayer.campusconnectbackend.dto.auth.request.RegisterRequest;
import com.rbslayer.campusconnectbackend.dto.auth.response.AuthResponse;
import com.rbslayer.campusconnectbackend.entity.Role;
import com.rbslayer.campusconnectbackend.entity.User;
import com.rbslayer.campusconnectbackend.repository.UserRepository;
import com.rbslayer.campusconnectbackend.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
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
        return null;
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return new AuthResponse("Login Successful");
    }
}
