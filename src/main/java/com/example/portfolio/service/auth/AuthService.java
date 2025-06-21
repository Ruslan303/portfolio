package com.example.portfolio.service.auth;

import com.example.portfolio.dto.request.LoginRequestDTO;
import com.example.portfolio.dto.request.RegisterRequestDTO;
import com.example.portfolio.dto.response.RegisterResponseDTO;
import com.example.portfolio.dto.response.TokenResponseDTO;

public interface AuthService {
    RegisterResponseDTO register(RegisterRequestDTO request);
    TokenResponseDTO login(LoginRequestDTO request);
    TokenResponseDTO refresh(String refreshToken);
}
