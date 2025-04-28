package com.example.portfolio.Entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ErrorMessage {
    USER_NOT_FOUND("User not found"),
    PORTFOLIO_NOT_FOUND("Portfoilo not found"),
    EDUCATION_NOT_FOUND("Education not found"),
    EXPERIENCE_NOT_FOUND("Experience not found"),
    PROJECT_NOT_FOUND("Project not found"),
    SKILL_NOT_FOUND("Skill not found");
    private final String message;


}
