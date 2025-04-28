package com.example.portfolio.controller;

import com.example.portfolio.dto.request.ProjectRequestDTO;
import com.example.portfolio.dto.response.ProjectResponseDTO;
import com.example.portfolio.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/projects")
public class ProjectController {


    private final ProjectService projectService;


    @PostMapping("/create")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDTO> createProject(ProjectRequestDTO projectRequestDTO) {
        return ResponseEntity.ok(projectService.createProject(projectRequestDTO));
    }



    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, ProjectRequestDTO projectRequestDTO) {
        return ResponseEntity.ok(projectService.updateProject(id, projectRequestDTO));
    }



    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<ProjectResponseDTO> getProjectById    (@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }



    @GetMapping("portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<ProjectResponseDTO>> getProjectsByPortfolioId(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(projectService.getProjectsByPortfolioId(portfolioId));
    }



    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProjectResponseDTO>> getProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }



    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }



}
