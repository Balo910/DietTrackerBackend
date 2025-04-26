package com.diettracker.backend.controllers;

import com.diettracker.backend.models.Fluid;
import com.diettracker.backend.models.Food;
import com.diettracker.backend.services.TagService;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api/dicts/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public Set<String> getAllTags() {
        return tagService.getAllTags();
    }

    @PostMapping("/food/{foodId}")
    public void addTagToFood(@PathVariable Long foodId, @RequestParam String tag) {
        tagService.addTagToFood(foodId, tag);
    }

    @DeleteMapping("/fluid/{fluidId}")
    public void removeTagFromFluid(@PathVariable Long fluidId, @RequestParam String tag) {
        tagService.removeTagFromFluid(fluidId, tag);
    }

    @GetMapping("/food")
    public Set<Food> getFoodsByTag(@RequestParam String tag) {
        return tagService.getFoodsByTag(tag);
    }

    @GetMapping("/fluid")
    public Set<Fluid> getFluidsByTag(@RequestParam String tag) {
        return tagService.getFluidsByTag(tag);
    }

    @GetMapping("/type/{type}")
    public Set<String> getTagsByType(@PathVariable String type) {
        return tagService.getTagsByType(type);
    }
}