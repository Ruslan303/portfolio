package com.example.portfolio.controller;
import com.example.portfolio.dto.request.ExperienceRequestDTO;
import com.example.portfolio.dto.response.ExperienceResponseDTO;
import com.example.portfolio.service.experience.ExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/experiences")
public class ExperienceController {


    private final ExperienceService experienceService;


    @PostMapping("/create")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExperienceResponseDTO> createExperience(@RequestBody ExperienceRequestDTO experienceRequestDTO) {
        return ResponseEntity.ok(experienceService.CreateExperience(experienceRequestDTO));
    }



    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExperienceResponseDTO> updateExperience(@PathVariable Long id, @RequestBody ExperienceRequestDTO experienceRequestDTO) {
        return ResponseEntity.ok(experienceService.updateExperience(id,experienceRequestDTO));
    }



    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<ExperienceResponseDTO> getExperienceById(@PathVariable Long id) {
        return ResponseEntity.ok(experienceService.getExperienceById(id));
    }



    @GetMapping ("/portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<ExperienceResponseDTO>> getExperiencesGetByPortfolio(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(experienceService.experiencesGetByPortfolioId(portfolioId));
    }



    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ExperienceResponseDTO>> getExperiences() {
        return ResponseEntity.ok(experienceService.getAllExperiences());
    }



    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long id) {
         experienceService.deleteExperience(id);
         return ResponseEntity.noContent().build();
    }



}
