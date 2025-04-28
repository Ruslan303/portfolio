package com.example.portfolio.service.skill;

import com.example.portfolio.dto.request.SkillRequestDTO;
import com.example.portfolio.dto.response.SkillResponseDTO;

import java.util.List;

public interface SkillService {
    SkillResponseDTO createSkill(SkillRequestDTO skillRequestDTO);
    SkillResponseDTO updateSkill(Long id ,SkillRequestDTO skillRequestDTO);
    SkillResponseDTO getSkillById(Long id);
    List<SkillResponseDTO> getSkills();
    List<SkillResponseDTO> getSkillsByPortfolioId(Long portfolioId);
    void  deleteSkill(Long id);
}
