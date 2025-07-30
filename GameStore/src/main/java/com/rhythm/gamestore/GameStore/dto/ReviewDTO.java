package com.rhythm.gamestore.GameStore.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    @Max(value = 10, message = "Rating must be between 1 and 10")
    @Min(value = 1, message = "Rating must be between 1 and 10")
    private int rating;
    @NotBlank(message = "Comment is required")
    private String comment;
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotNull(message = "Game ID is required")
    private Long gameId;
}
