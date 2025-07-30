package com.rhythm.gamestore.GameStore.service;

import com.rhythm.gamestore.GameStore.dto.GameDTO;
import com.rhythm.gamestore.GameStore.entity.Developer;
import com.rhythm.gamestore.GameStore.entity.Game;
import com.rhythm.gamestore.GameStore.exception.GameNotFoundException;
import com.rhythm.gamestore.GameStore.repository.DeveloperRepository;
import com.rhythm.gamestore.GameStore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public GameDTO createGame(GameDTO gameDTO) {
        System.out.println("Inside createGame - developerId: " + gameDTO.getDeveloperId());  // Check again here

        if (gameDTO.getDeveloperId() == null) {
            throw new IllegalArgumentException("DeveloperId is null");
        }
        Game game = new Game();
        game.setTitle(gameDTO.getTitle());
        game.setGenre(gameDTO.getGenre());

        Developer dev = developerRepository.findById(gameDTO.getDeveloperId())
                .orElseThrow(()-> new RuntimeException("Developer not found"));
        game.setDeveloper(dev);
        Game savedGame = gameRepository.save(game);
        GameDTO responseDTO = new GameDTO();
        responseDTO.setId(savedGame.getId());
        responseDTO.setTitle(savedGame.getTitle());
        responseDTO.setGenre(savedGame.getGenre());
        responseDTO.setDeveloperId(savedGame.getDeveloper().getId());

        return responseDTO;

    }

    @Override
    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream().map(game->{
            GameDTO gameDTO = new GameDTO();
            gameDTO.setId(game.getId());
            gameDTO.setTitle(game.getTitle());
            gameDTO.setGenre(game.getGenre());
            gameDTO.setDeveloperId(game.getDeveloper().getId());
            return gameDTO;
        }).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public GameDTO getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(()-> new GameNotFoundException("Game not found"));
        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setTitle(game.getTitle());
        gameDTO.setGenre(game.getGenre());
        gameDTO.setDeveloperId(game.getDeveloper().getId());
        return gameDTO;
    }

    @Override
    public void deleteGameById(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new GameNotFoundException("Game not found with id: " + id);
        }
        gameRepository.deleteById(id);
    }

}
