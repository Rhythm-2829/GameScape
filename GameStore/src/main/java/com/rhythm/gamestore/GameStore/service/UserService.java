package com.rhythm.gamestore.GameStore.service;

import com.rhythm.gamestore.GameStore.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    void deleteUserById(Long id);
}
