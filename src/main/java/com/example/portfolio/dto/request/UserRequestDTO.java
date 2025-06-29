package com.example.portfolio.dto.request;
import com.example.portfolio.Entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDTO {

    //static final String PASSWORD_REGEX = "^(?=.[a-z])(?=.[A-Z])(?=.*\\d).{4,20}$";

    @NotBlank(message = "{validation.user.lastname.notblank}")
    @Size(max = 100, message = "{validation.user.lastname.size}")
    String firstName;

    @NotBlank(message = "{validation.user.lastname.notblank}")
    @Size(max = 100, message = "{validation.user.lastname.size}")
    String lastName;

    @NotBlank(message = "{validation.user.username.notblank}")
    @Size(min = 5, max = 50, message = "{validation.user.username.size}")
    String username;

    @NotBlank(message = "{validation.user.email.notblank}")
    @Email(message = "{validation.user.email.email}")
    @Size(max = 254, message = "{validation.user.email.size}")
    String email;

    //    @NotBlank(message = "{validation.user.password.notblank}")
//    @Size(min = 4, max = 20, message = "{validation.user.password.size}")
//    @Pattern(regexp = PASSWORD_REGEX, message = "{validation.user.password.pattern}")
    @JsonIgnore
    String password;

    @JsonIgnore //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "{validation.user.role.notnull}")
    UserRole role = UserRole.ADMIN;
}

