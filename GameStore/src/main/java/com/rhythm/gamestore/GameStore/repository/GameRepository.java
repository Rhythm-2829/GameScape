package com.rhythm.gamestore.GameStore.repository;

import com.rhythm.gamestore.GameStore.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
