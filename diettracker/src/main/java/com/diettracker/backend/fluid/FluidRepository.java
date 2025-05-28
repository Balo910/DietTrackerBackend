package com.diettracker.backend.fluid;

import com.diettracker.backend.fluid.Fluid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FluidRepository extends JpaRepository<Fluid, Long> {
    List<Fluid> findByNameContainingIgnoreCase(String name);
}