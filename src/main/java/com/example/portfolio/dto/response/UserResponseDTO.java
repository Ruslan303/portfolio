package com.example.portfolio.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String password;
    private List<Long> portfolioIds;

}
