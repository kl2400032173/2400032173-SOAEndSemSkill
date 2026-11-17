package com.example.tourism.controller;

import com.example.tourism.entity.Tourist;
import com.example.tourism.repository.TouristRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    private final TouristRepository touristRepository;

    public TouristController(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    @GetMapping
    public List<Tourist> getAllTourists() {
        return touristRepository.findAll();
    }
}