package com.example.portfolio.dto.request;

import com.example.portfolio.Entity.enums.SkillLevel;
import lombok.Data;

@Data
public class SkillRequestDTO {
    private String name;
    private SkillLevel skillLevel;
    private Long portfolioId;
}
