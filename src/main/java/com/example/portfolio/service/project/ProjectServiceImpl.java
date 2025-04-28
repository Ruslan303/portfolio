package com.example.portfolio.service.project;

import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.Project;
import com.example.portfolio.Entity.enums.ErrorMessage;
import com.example.portfolio.configuration.mapper.ProjectMapper;
import com.example.portfolio.dto.request.PortfolioRequestDTO;
import com.example.portfolio.dto.request.ProjectRequestDTO;
import com.example.portfolio.dto.response.PortfolioResponseDTO;
import com.example.portfolio.dto.response.ProjectResponseDTO;
import com.example.portfolio.exception.PortfoiloException;
import com.example.portfolio.exception.ProjectException;
import com.example.portfolio.repository.PortfolioRepository;
import com.example.portfolio.repository.ProjectRepository;
import com.example.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final PortfolioRepository portfolioRepository;
    @Override
    public ProjectResponseDTO createProject(ProjectRequestDTO projectRequestDTO) {
        Portfolio portfolio = portfolioRepository.findById(projectRequestDTO.getPortfolioId()).
                orElseThrow(() -> new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage()+ " " + projectRequestDTO.getPortfolioId()));
        Project project = projectMapper.toEntity(projectRequestDTO, portfolio);
       return projectMapper.toResponse(projectRepository.save(project));
    }
    @Override
    public ProjectResponseDTO updateProject(Long id,ProjectRequestDTO projectRequestDTO) {
        Project project = projectRepository.findById(id).orElseThrow(()-> new ProjectException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()+ " " +id));
        projectMapper.updateEntity(projectRequestDTO,project);
        return projectMapper.toResponse(projectRepository.save(project));
    }
    @Override
    public ProjectResponseDTO getProjectById(Long id)  {
        return projectRepository.findById(id).map(projectMapper::toResponse).orElseThrow(()->new ProjectException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()+ " " +id));
    }
    @Override
    public List<ProjectResponseDTO> getProjectsByPortfolioId(Long portfolioId)  {
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(()->new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage()+ " " +portfolioId));
        return portfolio.getProjects().stream().map(projectMapper:: toResponse).collect(Collectors.toList());
    }
    @Override
    public List <ProjectResponseDTO> getProjects(){
        return projectRepository.findAll().stream().map(projectMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void deleteProject(Long id)  {
        Project project= projectRepository.findById(id).orElseThrow(( )->new ProjectException(ErrorMessage.PROJECT_NOT_FOUND.getMessage()+ " " +id));
        projectRepository.delete(project);
    }



}
