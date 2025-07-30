package com.rhythm.gamestore.GameStore.service;

import com.rhythm.gamestore.GameStore.dto.ReviewDTO;
import com.rhythm.gamestore.GameStore.entity.Game;
import com.rhythm.gamestore.GameStore.entity.Review;
import com.rhythm.gamestore.GameStore.entity.User;
import com.rhythm.gamestore.GameStore.exception.ReviewNotFoundException;
import com.rhythm.gamestore.GameStore.repository.GameRepository;
import com.rhythm.gamestore.GameStore.repository.ReviewRepository;
import com.rhythm.gamestore.GameStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        System.out.println("Inside createReview - userId: " + reviewDTO.getUserId());
        System.out.println("Inside createReview - gameId: " + reviewDTO.getGameId());
        if(reviewDTO.getUserId() == null || reviewDTO.getGameId() == null){
            throw new IllegalArgumentException("User or Game Id is null");
        }
        User user = userRepository.findById(reviewDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));
        Game game = gameRepository.findById(reviewDTO.getGameId())
                .orElseThrow(()-> new RuntimeException("Game not found"));
        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        review.setUser(user);
        review.setGame(game);
        Review savedReview = reviewRepository.save(review);
        ReviewDTO responseDTO = new ReviewDTO();
        responseDTO.setId(savedReview.getId());
        responseDTO.setRating(savedReview.getRating());
        responseDTO.setComment(savedReview.getComment());
        responseDTO.setUserId(savedReview.getUser().getId());
        responseDTO.setGameId(savedReview.getGame().getId());
        return responseDTO;
    }

    @Override
    public List<ReviewDTO> getReviewsByGameId(Long gameId) {
        return reviewRepository.findByGameId(gameId).stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(review.getId());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setComment(review.getComment());
            reviewDTO.setUserId(review.getUser().getId());
            reviewDTO.setGameId(review.getGame().getId());
            return reviewDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(Long userId) {

        return reviewRepository.findById(userId).stream().map(review -> {
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.setId(review.getId());
            reviewDTO.setRating(review.getRating());
            reviewDTO.setComment(review.getComment());
            reviewDTO.setUserId(review.getUser().getId());
            reviewDTO.setGameId(review.getGame().getId());
            return reviewDTO;
        }).collect(Collectors.toList());
    }
    @Override
    public void deleteReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            reviewRepository.delete(review.get());
        } else {
            throw new ReviewNotFoundException("Review not found with this id");
        }
    }
}
