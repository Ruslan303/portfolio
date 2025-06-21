package com.example.portfolio.service.user;

import com.example.portfolio.Entity.User;
import com.example.portfolio.Entity.enums.ErrorMessage;
import com.example.portfolio.Entity.enums.UserRole;
import com.example.portfolio.configuration.mapper.UserMapper;
import com.example.portfolio.dto.request.ResetPasswordRequestDTO;
import com.example.portfolio.dto.request.UserFilterRequestDTO;
import com.example.portfolio.dto.request.UserRequestDTO;
import com.example.portfolio.dto.response.UserResponseDTO;
import com.example.portfolio.exception.AdminException;
import com.example.portfolio.exception.EmailException;
import com.example.portfolio.exception.UserException;
import com.example.portfolio.repository.UserRepository;
import io.micrometer.common.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override/**/
    public UserResponseDTO createUser(UserRequestDTO request) {
        ensureUsernameAndEmailAreUnique(request.getUsername(), request.getEmail(), null);

        String password = request.getRole() == UserRole.ADMIN
                ? "Admin123" /*PasswordGenerator.generatePassword(20)*/
                : request.getPassword();


        User newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser = userRepository.save(newUser);
        //if(request.getRole() == UserRole.ADMIN) mailService.sendSimpleEmail(to, subject, text);
        return userMapper.toResponse(newUser);
    }                               /**/

    @Override
    public UserResponseDTO updateUser(Long userId, UserRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserException(ErrorMessage.USER_NOT_FOUND.getMessage() + " " + userId));

        ensureUsernameAndEmailAreUnique(request.getUsername(), request.getEmail(), userId);

        userMapper.updateEntity(request,user);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override/**/
    public void resetPassword(String username, ResetPasswordRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, request.getCurrentPassword())
        );
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        if (request.getCurrentPassword().equals(request.getNewPassword())) {
            throw new RuntimeException("Same password");
        }
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UserException(ErrorMessage.USER_NOT_FOUND.getMessage() + " " + username));
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }          /**/

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserResponseDTO getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::toResponse)
                .orElseThrow(()->new UserException(ErrorMessage.USER_NOT_FOUND.getMessage() + " " + userId));
    }

    @Override
    public Page<UserResponseDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public Page<UserResponseDTO>filterUsers(UserFilterRequestDTO request, Pageable pageable) {
        return userRepository.filterUsers(
                        request.getUsername(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getRole(),
                        request.getEducationInstitution(),
                        request.getEducationDegree(),
                        request.getExperienceCompany(),
                        request.getExperiencePosition(),
                        request.getSkillName(),
                        pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new UserException(ErrorMessage.USER_NOT_FOUND.getMessage() + " " + userId));
        if(user.getRole() == UserRole.ADMIN) {
            throw new AdminException(ErrorMessage.ADMIN_DELETE_FORBIDDEN, userId);
        }
        userRepository.delete(user);
    }


    private void ensureUsernameAndEmailAreUnique(String username, String email, @Nullable Long currentUserId) {
        List<User> users = userRepository.findAllByUsernameOrEmail(username, email);

        for (User user : users) {
            if (!user.getId().equals(currentUserId)) {
                if (user.getUsername().equals(username)) {
                    throw new UserException(ErrorMessage.USERNAME_ALREADY_TAKEN.getMessage()+ " " + username);
                }
                if (user.getEmail().equals(email)) {
                    throw new EmailException(ErrorMessage.EMAIL_ALREADY_TAKEN);
                }
            }
        }
    }
}


/*

    @Override
    public UserResponse createUser(UserRequest request) {
        Optional<UserEntity> user = userRepository.findByUsernameOrEmail(request.getEmail(), request.getUsername());
        if (user.isPresent()) {
            if(user.get().getUsername().equals(request.getUsername())) {
                throw new RuntimeException("Username is already taken");
            }
            else {
                throw new RuntimeException("Email is already taken");
            }
        }
        UserEntity newUser = userMapper.toEntity(request);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toResponse(userRepository.save(newUser));
    }


 */
