package com.rhythm.gamestore.GameStore.controller;

import com.rhythm.gamestore.GameStore.dto.DeveloperDTO;
import com.rhythm.gamestore.GameStore.service.DeveloperService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class DeveloperController {
    @Autowired
    private DeveloperService developerService;

    @PostMapping
    public DeveloperDTO createDeveloper(@RequestBody @Valid DeveloperDTO developerDTO){
        return developerService.createDeveloper(developerDTO);
    }
    @GetMapping
    public List<DeveloperDTO> getAllDevelopers(){
        return developerService.getAllDevelopers();
    }
    @GetMapping("/{id}")
    public DeveloperDTO getDeveloperById(@PathVariable Long id){
        return developerService.getDeveloperById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable @NotNull(message = "Developer Id cannot be null") Long id){
        developerService.deleteDeveloperById(id);
    }
}
