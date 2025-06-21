package com.example.portfolio.controller;

import com.example.portfolio.dto.request.LoginRequestDTO;
import com.example.portfolio.dto.request.RegisterRequestDTO;
import com.example.portfolio.dto.response.RegisterResponseDTO;
import com.example.portfolio.dto.response.TokenResponseDTO;
import com.example.portfolio.service.auth.AuthService;
import com.example.portfolio.service.auth.AuthServiceImpl;
import com.example.portfolio.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        RegisterResponseDTO response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginRequestDTO request,
                                                  HttpServletResponse response) {
        TokenResponseDTO tokenResponse = authService.login(request);
        CookieUtil.addRefreshToken(response, tokenResponse.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponseDTO> refresh(HttpServletRequest request,
                                                 HttpServletResponse response) {
        TokenResponseDTO tokenResponse = authService.refresh(CookieUtil.getRefreshToken(request));
        CookieUtil.deleteRefreshToken(response);
        CookieUtil.addRefreshToken(response, tokenResponse.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    } /* delete + add = update ? */

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {                  //acces token üçün blackList məntiqi də tətbiq etmək olar, bəlkə edərəm nəvaxtsa...
        CookieUtil.deleteRefreshToken(response);
        return ResponseEntity.ok().build();
    }
}
