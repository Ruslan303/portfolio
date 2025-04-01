package com.example.portfolio.service;

import com.example.portfolio.Entity.User;
import com.example.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;



    public Optional<User> findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            System.out.println("User found: " + user.getUsername());
        } else {
            System.out.println("User Not found: " + id);
        }
        return userOptional; // Optional<User> qaytarırıq
    }
    public User createUser( User user) {
        return userRepository.save(user);
    }
    public List<User> GetAllUser (){
        return userRepository.findAll();
    }
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        modelMapper.map(userDetails,user);
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
