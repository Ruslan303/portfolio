package com.example.portfolio.configuration.mapper;

import com.example.portfolio.dto.request.UserRequestDTO;
import com.example.portfolio.dto.request.RegisterRequestDTO;
import com.example.portfolio.dto.response.RegisterResponseDTO;
import com.example.portfolio.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,unmappedTargetPolicy = ReportingPolicy.WARN )
public interface AuthMapper {
    UserRequestDTO registerRequestToUserRequest(RegisterRequestDTO request);

    RegisterResponseDTO userResponseToRegisterResponse(UserResponseDTO userResponse);
}
