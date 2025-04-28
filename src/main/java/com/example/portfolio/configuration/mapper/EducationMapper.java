package com.example.portfolio.configuration.mapper;
import com.example.portfolio.Entity.Education;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.dto.request.EducationRequestDTO;
import com.example.portfolio.dto.response.EducationResponseDTO;
import org.mapstruct.*;


@Mapper (componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy = ReportingPolicy.WARN)
public interface EducationMapper {
    @Mapping(target = ("portfolioId"), source ="portfolio.id" )
    EducationResponseDTO toResponse(Education education);


    @Mapping(target ="id", ignore = true )
    @Mapping(target ="portfolio", source = "portfolio" )
    @Mapping(target = "description", source = "educationRequestDTO.description")
    Education toEntity(EducationRequestDTO educationRequestDTO, Portfolio portfolio);


    @Mapping (target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void updateEntity(EducationRequestDTO request, @MappingTarget Education education);


}
