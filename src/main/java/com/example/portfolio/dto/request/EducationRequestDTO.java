package com.example.portfolio.dto.request;
import com.example.portfolio.Entity.enums.DegreeLevel;
import lombok.Data;
import java.time.LocalDate;
@Data
public class EducationRequestDTO {

    private String institution;
    private DegreeLevel degree;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long portfolioId;
}
