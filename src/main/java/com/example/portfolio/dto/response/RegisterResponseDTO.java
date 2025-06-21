package com.example.portfolio.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class RegisterResponseDTO {
    Long id;
    String firstName;
    String lastName;
    String username;
    String email;
}
