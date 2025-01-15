package com.backend.crud.controllers;

import com.backend.crud.entities.Users;
import com.backend.crud.services.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200/")

public class UsersController {

    @Autowired
    UsersServiceImpl usersServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Users> saveUser(@RequestBody Users user) {
        try {
            Users savedUser = usersServiceImpl.saveUser(user);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        try {
            Users updatedUser = usersServiceImpl.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Users>> getAllUsers() {
        return new ResponseEntity<>(usersServiceImpl.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        Optional<Users> user = usersServiceImpl.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Users> deleteUserById(@PathVariable Long id) {
        Optional<Users> user = usersServiceImpl.getUserById(id);
        if (user.isPresent()) {
            usersServiceImpl.deleteUserById(user.get().getId());
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
