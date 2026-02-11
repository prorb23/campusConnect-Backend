package com.rbslayer.campusconnectbackend.service;

import com.rbslayer.campusconnectbackend.dto.auth.request.LoginRequest;
import com.rbslayer.campusconnectbackend.dto.auth.request.RegisterRequest;
import com.rbslayer.campusconnectbackend.dto.auth.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
