package com.example.portfolio.service.auth;
import com.example.portfolio.Entity.RefreshToken;
import com.example.portfolio.Entity.User;
import com.example.portfolio.configuration.mapper.AuthMapper;
import com.example.portfolio.dto.request.LoginRequestDTO;
import com.example.portfolio.dto.request.RegisterRequestDTO;
import com.example.portfolio.dto.response.RegisterResponseDTO;
import com.example.portfolio.dto.response.TokenResponseDTO;
import com.example.portfolio.repository.RefreshTokenRepository;
import com.example.portfolio.securty.CustomUserDetails;
import com.example.portfolio.securty.CustomUserDetailsServiceImpl;
import com.example.portfolio.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServiceImpl jwtService;
    private final UserServiceImpl userService;
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final AuthMapper authMapper;

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO request) {
        return authMapper.userResponseToRegisterResponse(
                userService.createUser(
                        authMapper.registerRequestToUserRequest(request)
                )
        );
    }

    @Override
    public TokenResponseDTO login(LoginRequestDTO request) {
        // İstifadəçi adı və şifrəni yoxla
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

//            // İstifadəçi məlumatlarını əldə et
//            UserEntity user = userRepository.findByUserName(request.getUserName())
//                    .orElseThrow(() -> new RuntimeException("İstifadəçi adı və ya şifrə yalnışdır!"));

        UserDetails user = customUserDetailsService.loadUserByUsername(request.getUsername());

        // Token yaradılır (istifadəçi məlumatları daxil edilir)
        String jwtAccessToken = jwtService.generateAccessToken(user);
        String jwtRefreshToken = jwtService.generateRefreshToken(user);

        return TokenResponseDTO.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }

    @Override
    public TokenResponseDTO refresh(String oldRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(oldRefreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        if (refreshToken.isExpired()) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        User user = refreshToken.getUser();

        String newAccessToken = jwtService.generateAccessToken(new CustomUserDetails(user));
        String newRefreshToken = jwtService.generateRefreshToken(new CustomUserDetails(user));

        return TokenResponseDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
