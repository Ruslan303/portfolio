package com.example.portfolio.service.user;

import com.example.portfolio.Entity.Portfolio;
import com.example.portfolio.Entity.User;
import com.example.portfolio.Entity.enums.ErrorMessage;
import com.example.portfolio.configuration.mapper.UserMapper;
import com.example.portfolio.dto.request.UserRequestDTO;
import com.example.portfolio.dto.response.UserResponseDTO;
import com.example.portfolio.exception.PortfoiloException;
import com.example.portfolio.exception.UserException;
import com.example.portfolio.repository.PortfolioRepository;
import com.example.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PortfolioRepository portfolioRepository;
    @Override
    public UserResponseDTO  createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }
    @Override
    public UserResponseDTO  updateUser(Long id,UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id).orElseThrow(()->new UserException(ErrorMessage.USER_NOT_FOUND.getMessage()+ " " +id));
        userMapper.updateEntity(userRequestDTO, user);
        return userMapper.toResponse(user);
    }
    @Override
    public UserResponseDTO getUserById(Long id) {
         return userRepository.findById(id).map(userMapper::toResponse).orElseThrow(()->new UserException(ErrorMessage.USER_NOT_FOUND.getMessage()+ " " +id));
    }
    @Override
    public UserResponseDTO getUserByPortfolioId(Long portfolioId) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(()->new PortfoiloException(ErrorMessage.PORTFOLIO_NOT_FOUND.getMessage()+ " " +portfolioId));
        return  userMapper.toResponse(portfolio.getUser());
    }
    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new UserException(ErrorMessage.USER_NOT_FOUND.getMessage()+ " " +id));
        userRepository.delete(user);
    }
}
