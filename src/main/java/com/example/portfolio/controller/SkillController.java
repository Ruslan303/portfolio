package com.example.portfolio.controller;
import com.example.portfolio.dto.request.SkillRequestDTO;
import com.example.portfolio.dto.response.SkillResponseDTO;
import com.example.portfolio.service.skill.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/skills")
public class SkillController {


    private final SkillService skillService;


    @PostMapping("/create")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillResponseDTO> createSkill(SkillRequestDTO skillRequestDTO) {
        return ResponseEntity.ok(skillService.createSkill(skillRequestDTO));
    }



    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillResponseDTO> updateSkill(@PathVariable Long id, SkillRequestDTO skillRequestDTO) {
        return ResponseEntity.ok(skillService.updateSkill(id, skillRequestDTO));
    }



    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<SkillResponseDTO> getSkillById(@PathVariable Long id) {
        return ResponseEntity.ok(skillService.getSkillById(id));
    }



    @GetMapping("portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<SkillResponseDTO>> getSkillsByPortfolioId(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(skillService.getSkillsByPortfolioId(portfolioId));
    }



    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<SkillResponseDTO>> getSkills() {
        return ResponseEntity.ok(skillService.getSkills());
    }



    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SkillResponseDTO> deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return ResponseEntity.noContent().build();
    }



}
