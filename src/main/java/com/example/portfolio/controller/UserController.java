package com.example.portfolio.controller;

import com.example.portfolio.dto.UserDetails;
import com.example.portfolio.Entity.User;
import com.example.portfolio.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private final UserServiceImpl userServiceimpl;


    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userServiceimpl.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userServiceimpl.GetAllUser(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userServiceimpl.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDetails userDetails) {
        return new ResponseEntity<>(userServiceimpl.updateUser(id, userDetails), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceimpl.deleteUser(id);
    }




}
