package com.diettracker.backend.food;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFood() {
        return foodRepository.findAll();
    }

    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    public Optional<Food> updateFood(Long id, Food updatedFood) {
        return foodRepository.findById(id)
                .map(food -> {
                    food.setName(updatedFood.getName());
                    food.setWeight(updatedFood.getWeight());
                    food.setCalories(updatedFood.getCalories());
                    food.setProteins(updatedFood.getProteins());
                    food.setFats(updatedFood.getFats());
                    food.setCarbs(updatedFood.getCarbs());
                    return foodRepository.save(food);
                });
    }

    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public List<Food> searchFoodByName(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }

}