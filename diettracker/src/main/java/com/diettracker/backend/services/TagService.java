package com.diettracker.backend.services;

import com.diettracker.backend.models.Food;
import com.diettracker.backend.repositories.FoodRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class
TagService {

    private final FoodRepository foodRepository;

    public TagService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Set<String> getAllTags() {
        return foodRepository.findAll().stream()
                .flatMap(food -> food.getTags().stream())
                .collect(Collectors.toSet());
    }
}