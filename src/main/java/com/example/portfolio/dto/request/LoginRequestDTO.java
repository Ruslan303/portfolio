package com.example.portfolio.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequestDTO {

    @NotBlank(message = "Username cannot be blank" )
    @Size(min= 5,max = 50,message = "Password must be 4-20 characters")
    String username;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, max = 20, message = "Password must be 4-20 characters")
    String password;

}
