package com.example.portfolio.service.user;


import com.example.portfolio.dto.request.ResetPasswordRequestDTO;
import com.example.portfolio.dto.request.UserFilterRequestDTO;
import com.example.portfolio.dto.request.UserRequestDTO;
import com.example.portfolio.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

   UserResponseDTO createUser(UserRequestDTO request);
   UserResponseDTO updateUser(Long userId, UserRequestDTO request);
   void resetPassword(String username, ResetPasswordRequestDTO request);
   boolean existsByEmail(String email);
   boolean existsByUsername(String username);
   UserResponseDTO getUserById(Long userId);
   Page<UserResponseDTO> getAllUsers(Pageable pageable);
   Page<UserResponseDTO>filterUsers(UserFilterRequestDTO request, Pageable pageable);
   void deleteUser(Long userId);
}


