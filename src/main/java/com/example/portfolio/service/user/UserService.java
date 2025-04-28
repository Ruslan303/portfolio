package com.example.portfolio.service.user;


import com.example.portfolio.dto.request.UserRequestDTO;
import com.example.portfolio.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
   UserResponseDTO createUser(UserRequestDTO userRequestDTO);
   UserResponseDTO  updateUser(Long id,UserRequestDTO userRequestDTO);
   UserResponseDTO getUserById(Long id);
   UserResponseDTO getUserByPortfolioId(Long PortfolioId);
   List<UserResponseDTO> getAllUsers();
   void deleteUserById(Long id);


}
