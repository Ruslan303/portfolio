package com.example.portfolio.controller;
import com.example.portfolio.dto.request.EducationRequestDTO;
import com.example.portfolio.dto.response.EducationResponseDTO;
import com.example.portfolio.service.education.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/educations")


public class EducationController {


    private final EducationService educationService;


    @PostMapping("create")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EducationResponseDTO> createEducations (@RequestBody EducationRequestDTO educationRequestDTO) {
        return ResponseEntity.ok(educationService.createEducation(educationRequestDTO));
    }



    @PutMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EducationResponseDTO> updateEducation (@PathVariable Long id,@RequestBody EducationRequestDTO educationRequestDTO) {
        return ResponseEntity.ok(educationService.updateEducation(id, educationRequestDTO));
    }



    @GetMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<EducationResponseDTO> getEducation (@PathVariable Long id) {
        return ResponseEntity.ok(educationService.getEducationByid(id));
    }



    @GetMapping("portfolio/{portfolioId}")
    //@PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    public ResponseEntity<List<EducationResponseDTO>> getEducationsByPortfolioId (@PathVariable Long portfolioId) {
        return ResponseEntity.ok(educationService.getEducationsBYPortfolioId(portfolioId));
    }



    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EducationResponseDTO>> getEducations () {
        return ResponseEntity.ok(educationService.getAllEducation());
    }



    @DeleteMapping("{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEducation (@PathVariable Long id) {
        educationService.deleteEducation(id);
        return ResponseEntity.noContent().build();
    }




}
