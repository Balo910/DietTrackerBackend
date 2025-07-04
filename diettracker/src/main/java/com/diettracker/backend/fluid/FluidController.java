package com.diettracker.backend.fluid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fluid")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Fluid", description = "Zarządzanie napojami")
public class FluidController {

    private final FluidRepository fluidRepository;

    public FluidController(FluidRepository fluidRepository) {
        this.fluidRepository = fluidRepository;
    }

    @GetMapping
    public List<Fluid> getAllFluids() {
        return fluidRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fluid> getFluidById(@PathVariable Long id) {
        Optional<Fluid> fluid = fluidRepository.findById(id);
        return fluid.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Fluid addFluid(@RequestBody Fluid fluid) {
        return fluidRepository.save(fluid);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fluid> updateFluid(@PathVariable Long id, @RequestBody Fluid updatedFluid) {
        if (!fluidRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        updatedFluid.setId(id);
        return ResponseEntity.ok(fluidRepository.save(updatedFluid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFluid(@PathVariable Long id) {
        if (!fluidRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        fluidRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}