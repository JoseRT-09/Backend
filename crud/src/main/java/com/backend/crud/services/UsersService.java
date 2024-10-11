package com.backend.crud.services;

import com.backend.crud.entities.Users;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    Users saveUser(Users user);
    Users updateUser(Users user);
    List<Users> getUsers();
    Optional<Users> getUserById(Long id);
    void deleteUserById(Long id);
}
