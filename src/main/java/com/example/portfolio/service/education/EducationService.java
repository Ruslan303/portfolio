package com.example.portfolio.service.education;
import com.example.portfolio.dto.request.EducationRequestDTO;
import com.example.portfolio.dto.response.EducationResponseDTO;
import java.util.List;

public interface EducationService {
     EducationResponseDTO createEducation(EducationRequestDTO educationRequestDTO);

    EducationResponseDTO updateEducation(Long id,EducationRequestDTO educationRequestDTO);

    EducationResponseDTO getEducationByid(Long id);

    List<EducationResponseDTO> getEducationsBYPortfolioId(Long portfolioId);

    List<EducationResponseDTO> getAllEducation();

    void  deleteEducation(Long id );
}
