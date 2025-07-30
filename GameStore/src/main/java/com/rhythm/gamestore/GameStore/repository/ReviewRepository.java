package com.rhythm.gamestore.GameStore.repository;

import com.rhythm.gamestore.GameStore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByGameId(Long gameId);
    List<Review> findByUserId(Long userId);
}
