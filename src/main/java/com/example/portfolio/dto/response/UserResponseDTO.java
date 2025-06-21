package com.example.portfolio.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private List<Long> portfolioIds;

}
