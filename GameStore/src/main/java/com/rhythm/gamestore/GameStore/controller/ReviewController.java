package com.rhythm.gamestore.GameStore.controller;

import com.rhythm.gamestore.GameStore.dto.ReviewDTO;
import com.rhythm.gamestore.GameStore.service.ReviewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reviews")
@Validated
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
//    @PostMapping("/create")
//    public ReviewDTO createReview(ReviewDTO reviewDTO){
//        return reviewService.createReview(reviewDTO);
//    }
    @PostMapping("/create")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody @Valid ReviewDTO reviewDTO){
        System.out.println("Received userId: " + reviewDTO.getUserId());
        System.out.println("Received gameId: " + reviewDTO.getGameId());
        ReviewDTO savedReview = reviewService.createReview(reviewDTO);
        return ResponseEntity.ok(savedReview);
    }
    @GetMapping("/game/{gameid}")
    public List<ReviewDTO> getReviewsByGameId(@PathVariable Long gameid){
        return reviewService.getReviewsByGameId(gameid);
    }
    @GetMapping("/user/{userid}")
    public List<ReviewDTO> getReviewsByUserId(@PathVariable Long userid){
        return reviewService.getReviewsByUserId(userid);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long id){
        reviewService.deleteReviewById(id);
        return ResponseEntity.ok("Review deleted successfully");
    }
}
