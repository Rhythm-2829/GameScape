package com.rhythm.gamestore.GameStore.repository;

import com.rhythm.gamestore.GameStore.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
}
