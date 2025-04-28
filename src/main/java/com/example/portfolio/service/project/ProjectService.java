package com.example.portfolio.service.project;

import com.example.portfolio.dto.request.ProjectRequestDTO;
import com.example.portfolio.dto.response.ProjectResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProjectService {
    ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO);

    ProjectResponseDTO updateProject(Long id,ProjectRequestDTO projectRequestDTO);

    ProjectResponseDTO getProjectById(Long id);

    List<ProjectResponseDTO> getProjectsByPortfolioId(Long portfolioId);

    List <ProjectResponseDTO> getProjects();

    void deleteProject(Long id);
}
