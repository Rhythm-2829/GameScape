package com.rhythm.gamestore.GameStore.service;

import com.rhythm.gamestore.GameStore.dto.DeveloperDTO;
import com.rhythm.gamestore.GameStore.entity.Developer;
import com.rhythm.gamestore.GameStore.exception.DeveloperNotFoundException;
import com.rhythm.gamestore.GameStore.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public DeveloperDTO createDeveloper(DeveloperDTO developerDTO) {
        Developer dev = new Developer();
        dev.setName(developerDTO.getName());
        dev.setWebsite(developerDTO.getWebsite());
        Developer savedDev = developerRepository.save(dev);
        DeveloperDTO responseDTO = new DeveloperDTO();
        responseDTO.setId(savedDev.getId());
        responseDTO.setName(savedDev.getName());
        responseDTO.setWebsite(savedDev.getWebsite());
        return responseDTO;
    }

    @Override
    public List<DeveloperDTO> getAllDevelopers() {
        return developerRepository.findAll().stream().map(dev ->{
            DeveloperDTO developerDTO = new DeveloperDTO();
            developerDTO.setId(dev.getId());
            developerDTO.setName(dev.getName());
            developerDTO.setWebsite(dev.getWebsite());
            return developerDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public DeveloperDTO getDeveloperById(Long id) {
        Developer dev = developerRepository.findById(id)
                .orElseThrow(()->new DeveloperNotFoundException("Developer not found"));
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setId(dev.getId());
        developerDTO.setName(dev.getName());
        developerDTO.setWebsite(dev.getWebsite());
        return developerDTO;
    }

    @Override
    public void deleteDeveloperById(Long id) {
        if(!developerRepository.existsById(id)) {
            throw new DeveloperNotFoundException("Developer not found with id: " + id);
        }
        developerRepository.deleteById(id);
    }
}
