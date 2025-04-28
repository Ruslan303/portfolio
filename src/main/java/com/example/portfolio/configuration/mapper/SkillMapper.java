package com.example.portfolio.configuration.mapper;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.Skill;
import com.example.portfolio.dto.request.SkillRequestDTO;
import com.example.portfolio.dto.response.SkillResponseDTO;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SkillMapper {


    @Mapping(target = "portfolioId", source = "portfolio.id")
    SkillResponseDTO toResponse(Skill skill);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", source = "portfolio")
    Skill toEntity(SkillRequestDTO request, Portfolio portfolio);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolio", ignore = true)
    void update(SkillRequestDTO request, @MappingTarget Skill skill);


}

