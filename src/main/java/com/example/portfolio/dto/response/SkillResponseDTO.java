package com.example.portfolio.dto.response;

import com.example.portfolio.Entity.enums.SkillLevel;
import lombok.Data;

@Data
public class SkillResponseDTO {
    private  Long id;
    private String name;
    private SkillLevel skillLevel;
    private Long portfolioId;
}
