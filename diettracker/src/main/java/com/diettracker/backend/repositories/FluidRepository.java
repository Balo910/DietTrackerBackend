package com.diettracker.backend.repositories;

import com.diettracker.backend.models.Fluid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FluidRepository extends JpaRepository<Fluid, Long> {
    List<Fluid> findByNameContainingIgnoreCase(String name);
}