package com.example.portfolio.service.experience;
import com.example.portfolio.dto.request.ExperienceRequestDTO;
import com.example.portfolio.dto.response.ExperienceResponseDTO;
import java.util.List;


public interface ExperienceService {

    ExperienceResponseDTO CreateExperience(ExperienceRequestDTO experienceRequestDTO);

    ExperienceResponseDTO updateExperience(Long id,ExperienceRequestDTO experienceRequestDTO);

    ExperienceResponseDTO getExperienceById(Long id);

    List<ExperienceResponseDTO> experiencesGetByPortfolioId(Long id);

    List<ExperienceResponseDTO> getAllExperiences();

    void deleteExperience(Long id);
}
