package com.diettracker.backend.food;

import com.diettracker.backend.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByNameContainingIgnoreCase(String name);
}