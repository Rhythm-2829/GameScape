package com.rhythm.gamestore.GameStore.service;

import com.rhythm.gamestore.GameStore.dto.GameDTO;

import java.util.List;

public interface GameService {
    GameDTO createGame(GameDTO gameDTO);
    List<GameDTO> getAllGames();
    GameDTO getGameById(Long id);
    void deleteGameById(Long id);
}
