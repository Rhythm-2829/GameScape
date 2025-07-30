package com.rhythm.gamestore.GameStore.controller;

import com.rhythm.gamestore.GameStore.dto.UserDTO;
import com.rhythm.gamestore.GameStore.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable @NotNull(message = "It can't be null") Long id){
        return userService.getUserById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable @NotNull(message = "Id should not be null") Long id){
        userService.deleteUserById(id);
    }
}
