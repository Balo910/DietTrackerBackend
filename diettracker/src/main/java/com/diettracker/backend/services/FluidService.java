package com.diettracker.backend.services;

import com.diettracker.backend.models.Fluid;
import com.diettracker.backend.repositories.FluidRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FluidService {

    private final FluidRepository fluidRepository;

    public FluidService(FluidRepository fluidRepository) {
        this.fluidRepository = fluidRepository;
    }

    public List<Fluid> getAllFluids() {
        return fluidRepository.findAll();
    }

    public Optional<Fluid> getFluidById(Long id) {
        return fluidRepository.findById(id);
    }

    public Fluid saveFluid(Fluid fluid) {
        return fluidRepository.save(fluid);
    }

    public Optional<Fluid> updateFluid(Long id, Fluid updatedFluid) {
        return fluidRepository.findById(id)
                .map(fluid -> {
                    fluid.setName(updatedFluid.getName());
                    fluid.setVolume(updatedFluid.getVolume());
                    fluid.setCalories(updatedFluid.getCalories());
                    return fluidRepository.save(fluid);
                });
    }

    public void deleteFluid(Long id) {
        fluidRepository.deleteById(id);
    }
}