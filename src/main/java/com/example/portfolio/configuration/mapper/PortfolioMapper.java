package com.example.portfolio.configuration.mapper;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.User;
import com.example.portfolio.dto.request.PortfolioRequestDTO;
import com.example.portfolio.dto.response.PortfolioResponseDTO;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        uses = {EducationMapper.class, ExperienceMapper.class, ProjectMapper.class, SkillMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PortfolioMapper {


    @Mapping(target = "userId", source = "user.id")
    PortfolioResponseDTO toResponse(Portfolio portfolio);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "skills", ignore = true)
    Portfolio toEntity(PortfolioRequestDTO portfolioRequestDTO ,User user);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "educations", ignore = true)
    @Mapping(target = "experiences", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "skills", ignore = true)
    void updateEntity(PortfolioRequestDTO portfolioRequestDTO, @MappingTarget Portfolio portfolio);


}
