package com.rhythm.gamestore.GameStore.controller;

import com.rhythm.gamestore.GameStore.dto.GameDTO;
import com.rhythm.gamestore.GameStore.service.GameService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/games")
@Validated
public class GameController {
    @Autowired
    private GameService gameService;
    @PostMapping("/create")
    public ResponseEntity<GameDTO> createGame(@RequestBody @Valid GameDTO gameDTO) {
        System.out.println("Received developerId: " + gameDTO.getDeveloperId());  // Check developerId value here
        GameDTO savedGame = gameService.createGame(gameDTO);
        return ResponseEntity.ok(savedGame);
    }
    @GetMapping
    public List<GameDTO> getAllGames(){
        return gameService.getAllGames();
    }
    @GetMapping("/{id}")
    public GameDTO getGameById(@PathVariable Long id){
        return gameService.getGameById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGameById(@PathVariable Long id) {
        gameService.deleteGameById(id);
        return ResponseEntity.ok("Game deleted successfully");
    }
}
