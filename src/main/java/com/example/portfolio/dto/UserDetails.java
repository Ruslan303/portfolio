package com.example.portfolio.dto;

import jakarta.persistence.Column;

public class UserDetails {
    @Column(unique=true,nullable=false)
    String username;
    String password;
}
