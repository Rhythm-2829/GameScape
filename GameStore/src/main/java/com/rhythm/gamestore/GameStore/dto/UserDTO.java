package com.rhythm.gamestore.GameStore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotBlank(message = "Username is required")
    private String username;
    @Email(message = "Email is invalid")
    private String email;
}
