package com.example.portfolio.dto.request;

import com.example.portfolio.Entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequestDTO {
    static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
    @NotBlank(message="Full name cannot be blank")
    @Size(min = 5,max=50,message="Full name must be 5-20 characters")
    String firstName;
    @NotBlank(message="Full name cannot be blank")
    @Size(min = 5,max=50,message="Full name must be 5-20 characters")
    String lastName;
    @NotBlank(message = "Username cannot be blank")
    @Size(min =5, max=20, message ="Username must be 5-20 charackters")
    String username;
    @NotBlank(message ="Password cannot be blank")
    @Size(min =4, max=20, message= "Password must be 4-20 characters")
    @Pattern(regexp = PASSWORD_REGEX,message = "Password must contain at least 1 uppercase letter (A-Z), 1 lowercase letter (a-z), and 1 digit (0-9)")
    String password;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    String email;
    @JsonIgnore
    UserRole role = UserRole.MEMBER;



}
