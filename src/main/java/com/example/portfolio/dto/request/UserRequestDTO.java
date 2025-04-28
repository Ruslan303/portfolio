package com.example.portfolio.dto.request;
import lombok.Data;
@Data
public class UserRequestDTO {
    private String username;
    private String password;
    private Long portfolioId;

}
