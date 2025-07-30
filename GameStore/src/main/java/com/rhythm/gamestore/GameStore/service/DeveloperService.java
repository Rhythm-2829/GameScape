package com.rhythm.gamestore.GameStore.service;

import com.rhythm.gamestore.GameStore.dto.DeveloperDTO;

import java.util.List;

public interface DeveloperService {
    DeveloperDTO createDeveloper(DeveloperDTO developer);
    List<DeveloperDTO> getAllDevelopers();
    DeveloperDTO getDeveloperById(Long id);
    void deleteDeveloperById(Long id);
}
