package com.rhythm.gamestore.GameStore.service;

import com.rhythm.gamestore.GameStore.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    ReviewDTO createReview(ReviewDTO review);
    List<ReviewDTO> getReviewsByGameId(Long gameId);
    List<ReviewDTO> getReviewsByUserId(Long userId);
    void deleteReviewById(Long id);
}
