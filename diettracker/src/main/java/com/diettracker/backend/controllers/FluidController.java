package com.diettracker.backend.controllers;

import com.diettracker.backend.models.Fluid;
import com.diettracker.backend.services.FluidService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fluid")
public class FluidController {

    private final FluidService fluidService;

    public FluidController(FluidService fluidService) {
        this.fluidService = fluidService;
    }

    @GetMapping
    public List<Fluid> getAllFluids() {
        return fluidService.getAllFluids();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fluid> getFluidById(@PathVariable Long id) {
        Optional<Fluid> fluid = fluidService.getFluidById(id);
        return fluid.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fluid createFluid(@RequestBody Fluid fluid) {
        return fluidService.saveFluid(fluid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fluid> updateFluid(@PathVariable Long id, @RequestBody Fluid updatedFluid) {
        Optional<Fluid> fluid = fluidService.updateFluid(id, updatedFluid);
        return fluid.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFluid(@PathVariable Long id) {
        fluidService.deleteFluid(id);
        return ResponseEntity.noContent().build();
    }
}