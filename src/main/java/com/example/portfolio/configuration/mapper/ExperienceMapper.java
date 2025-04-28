package com.example.portfolio.configuration.mapper;
import com.example.portfolio.Entity.Experience;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.dto.request.ExperienceRequestDTO;
import com.example.portfolio.dto.response.ExperienceResponseDTO;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ExperienceMapper {
    @Mapping(target = "portfolioId", source = "portfolio.id")
    ExperienceResponseDTO toResponse(Experience experience);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    @Mapping(target = "description", source = "experienceRequestDTO.description")
    Experience toEntity(ExperienceRequestDTO experienceRequestDTO, Portfolio portfolio);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void updateEntity(ExperienceRequestDTO experienceRequestDTO, @MappingTarget Experience experience);


}
