package com.diettracker.backend.services;

import com.diettracker.backend.models.Food;
import com.diettracker.backend.models.Fluid;
import com.diettracker.backend.repositories.FluidRepository;
import com.diettracker.backend.repositories.FoodRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagService {

    private final FoodRepository foodRepository;
    private final FluidRepository fluidRepository;

    public TagService(FoodRepository foodRepository, FluidRepository fluidRepository) {
        this.foodRepository = foodRepository;
        this.fluidRepository = fluidRepository;
    }

    private String normalizeTag(String tag) {
        return tag == null ? null : tag.trim().toLowerCase();
    }

    public Set<String> getAllTags() {
        Set<String> allTags = new HashSet<>();

        allTags.addAll(foodRepository.findAll().stream()
                .flatMap(food -> food.getTags().stream())
                .map(this::normalizeTag)
                .collect(Collectors.toSet()));

        allTags.addAll(fluidRepository.findAll().stream()
                .flatMap(fluid -> fluid.getTags().stream())
                .map(this::normalizeTag)
                .collect(Collectors.toSet()));

        return allTags;
    }

    public Set<String> getTagsByType(String type) {
        return switch (type.toLowerCase()) {
            case "food" -> foodRepository.findAll().stream()
                    .flatMap(food -> food.getTags().stream())
                    .map(this::normalizeTag)
                    .collect(Collectors.toSet());
            case "fluid" -> fluidRepository.findAll().stream()
                    .flatMap(fluid -> fluid.getTags().stream())
                    .map(this::normalizeTag)
                    .collect(Collectors.toSet());
            default -> throw new IllegalArgumentException("Nieprawidłowy typ. Dopuszczalne wartości: 'food' lub 'fluid'");
        };
    }

    public Set<Food> getFoodsByTag(String tag) {
        String normalizedTag = normalizeTag(tag);
        return new HashSet<>(foodRepository.findByTagsContaining(normalizedTag));
    }

    public Set<Fluid> getFluidsByTag(String tag) {
        String normalizedTag = normalizeTag(tag);
        return new HashSet<>(fluidRepository.findByTagsContaining(normalizedTag));
    }

    public void addTagToFood(Long foodId, String tag) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new EntityNotFoundException("Food not found"));
        food.getTags().add(normalizeTag(tag));
        foodRepository.save(food);
    }

    public void removeTagFromFluid(Long fluidId, String tag) {
        Fluid fluid = fluidRepository.findById(fluidId)
                .orElseThrow(() -> new EntityNotFoundException("Fluid not found"));
        fluid.getTags().remove(normalizeTag(tag));
        fluidRepository.save(fluid);
    }
}