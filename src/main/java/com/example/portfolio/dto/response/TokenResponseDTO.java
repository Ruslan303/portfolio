package com.example.portfolio.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TokenResponseDTO {
    String accessToken;

    @JsonIgnore // refreshtoken cookie yə yazılacaqsa, jsonignore etmək məntiqlidir sanki
    String refreshToken;
}
