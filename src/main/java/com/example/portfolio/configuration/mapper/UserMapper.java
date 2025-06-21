package com.example.portfolio.configuration.mapper;
import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.User;
import com.example.portfolio.dto.request.UserRequestDTO;
import com.example.portfolio.dto.response.UserResponseDTO;
import org.mapstruct.*;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring",
        uses = {PortfolioMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {


    @Mapping(target = "portfolioIds", source = "portfolios", qualifiedByName = "mapPortfoliosToPortfolioIds")
    UserResponseDTO toResponse(User user);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolios", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "portfolios", ignore = true)
    void updateEntity(UserRequestDTO userRequestDTO, @MappingTarget User user);


    @Named("mapPortfoliosToPortfolioIds")
    default List<Long> mapPortfoliosToPortfolioIds(List<Portfolio> portfolios) {
        if (portfolios == null) return null;
        return portfolios.stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());
    }



}

