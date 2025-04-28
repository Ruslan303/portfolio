package com.example.portfolio.service.experience;

import com.example.portfolio.Entity.Experience;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.enums.ErrorMessage;
import com.example.portfolio.configuration.mapper.ExperienceMapper;
import com.example.portfolio.dto.request.ExperienceRequestDTO;
import com.example.portfolio.dto.response.ExperienceResponseDTO;
import com.example.portfolio.exception.ExperienceException;
import com.example.portfolio.exception.PortfoiloException;
import com.example.portfolio.repository.ExperienceRepository;
import com.example.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {
    private final ExperienceRepository experienceRepository;
    private final PortfolioRepository portfolioRepository;
    private final ExperienceMapper experienceMapper;
    @Override
    public ExperienceResponseDTO CreateExperience(ExperienceRequestDTO experienceRequestDTO) {
        Portfolio portfolio = portfolioRepository.findById(experienceRequestDTO.getPortfolioId()).orElseThrow(() -> new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage() + " " + experienceRequestDTO.getPortfolioId()));
        Experience experience = experienceMapper.toEntity(experienceRequestDTO, portfolio);
        return experienceMapper.toResponse(experienceRepository.save(experience));
    }
    @Override
    public ExperienceResponseDTO updateExperience(Long id,ExperienceRequestDTO experienceRequestDTO) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new ExperienceException(ErrorMessage.EXPERIENCE_NOT_FOUND.getMessage() + " "+id ));
        experienceMapper.updateEntity(experienceRequestDTO, experience);
        return experienceMapper.toResponse(experienceRepository.save(experience));
    }
    @Override
    public ExperienceResponseDTO getExperienceById(Long id) {
        return experienceRepository.findById(id).map(experienceMapper ::toResponse).orElseThrow(() -> new ExperienceException(ErrorMessage.EXPERIENCE_NOT_FOUND.getMessage() + " " + id));
    }
    @Override
    public List<ExperienceResponseDTO> experiencesGetByPortfolioId(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(() -> new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage() + " " + id));
        return portfolio.getExperiences().stream().map(experienceMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<ExperienceResponseDTO> getAllExperiences() {
        return experienceRepository.findAll().stream().map(experienceMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void deleteExperience(Long id) {
        Experience experience = experienceRepository.findById(id).orElseThrow(() -> new ExperienceException(ErrorMessage.EXPERIENCE_NOT_FOUND.getMessage() + " " + id));
        experienceRepository.deleteById(id);
    }
}


