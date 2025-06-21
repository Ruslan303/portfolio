package com.example.portfolio.dto.request;

import com.example.portfolio.Entity.enums.DegreeLevel;
import com.example.portfolio.Entity.enums.UserRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFilterRequestDTO {
    String username;
    String firstName;
    String lastName;
    String email;
    UserRole role;
    String educationInstitution;
    DegreeLevel educationDegree;
    String experienceCompany;
    String experiencePosition;
    String skillName;
}

