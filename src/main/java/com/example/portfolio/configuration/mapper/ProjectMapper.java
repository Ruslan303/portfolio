package com.example.portfolio.configuration.mapper;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.Project;
import com.example.portfolio.dto.request.ProjectRequestDTO;
import com.example.portfolio.dto.response.ProjectResponseDTO;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
       nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProjectMapper {


    @Mapping(target = "portfolioId", source = "portfolio.id")
    ProjectResponseDTO toResponse(Project project);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "title", source = "request.title")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "portfolio", source = "portfolio")
    Project toEntity(ProjectRequestDTO request, Portfolio portfolio);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(ProjectRequestDTO request, @MappingTarget Project project);


}
