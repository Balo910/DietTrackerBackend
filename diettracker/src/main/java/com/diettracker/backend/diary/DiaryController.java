package com.diettracker.backend.diary;
import com.diettracker.backend.diaryfluid.*;
import com.diettracker.backend.diaryfood.*;
import com.diettracker.backend.fluid.Fluid;
import com.diettracker.backend.food.Food;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/diary")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Diary", description = "Zarządzanie dziennikiem żywieniowym użytkownika")

public class DiaryController {

    private final DiaryRepository diaryRepository;
    private final DiaryFoodRepository diaryFoodRepository;
    private final DiaryFluidRepository diaryFluidRepository;
    private final DiaryService diaryService;

    public DiaryController(DiaryRepository diaryRepository, DiaryFoodRepository diaryFoodRepository,
                           DiaryFluidRepository diaryFluidRepository, DiaryService diaryService) {
        this.diaryRepository = diaryRepository;
        this.diaryFoodRepository = diaryFoodRepository;
        this.diaryFluidRepository = diaryFluidRepository;
        this.diaryService = diaryService;
    }

    @GetMapping
    public List<Diary> getAllDiaries() {
        return diaryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable Long id) {
        Optional<Diary> diary = diaryRepository.findById(id);
        return diary.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Diary> createDiary(@RequestBody CreateDiaryRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Diary diary = new Diary();
        diary.setDate(request.getDate());
        diary.setDiaryOwner(username);
        Diary savedDiary = diaryRepository.save(diary);
        return ResponseEntity.ok(savedDiary);
    }

    @GetMapping("/with-foods")
    public List<DiaryWithFoodsAndFluidsDTO> getAllDiariesWithFoodsAndFluids() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Diary> diaries = diaryRepository.findByDiaryOwner(username);

        return diaries.stream().map(diary -> {
            List<DiaryFoodDTO> foodDTOs = diary.getDiaryFoods().stream()
                    .map(this::convertToFoodDTO)
                    .toList();

            List<DiaryFluidDTO> fluidDTOs = diary.getDiaryFluids().stream()
                    .map(this::convertToFluidDTO)
                    .toList();

            return new DiaryWithFoodsAndFluidsDTO(
                    diary.getId(),
                    diary.getDate(),
                    foodDTOs,
                    fluidDTOs
            );
        }).toList();
    }


    @PostMapping("/add-food")
    public ResponseEntity<DiaryFoodDTO> addFoodToDiary(@RequestBody AddDiaryFoodRequest request) {
        DiaryFood diaryFood = diaryService.addFoodToDiary(
                request.getDiaryId(),
                request.getFoodId(),
                request.getWeight()
        );
        return ResponseEntity.ok(convertToFoodDTO(diaryFood));
    }

    @PostMapping("/add-fluid")
    public ResponseEntity<DiaryFluidDTO> addFluidToDiary(@RequestBody AddDiaryFluidRequest request) {
        DiaryFluid diaryFluid = diaryService.addFluidToDiary(
                request.getDiaryId(),
                request.getFluidId(),
                request.getVolume()
        );
        return ResponseEntity.ok(convertToFluidDTO(diaryFluid));
    }

    @DeleteMapping("/{diaryId}/food/{foodId}")
    public ResponseEntity<Void> deleteDiaryFood(
            @PathVariable Long diaryId,
            @PathVariable Long foodId) {
        diaryService.deleteDiaryFood(diaryId, foodId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{diaryId}/fluid/{fluidId}")
    public ResponseEntity<Void> deleteDiaryFluid(
            @PathVariable Long diaryId,
            @PathVariable Long fluidId) {
        diaryService.deleteDiaryFluid(diaryId, fluidId);
        return ResponseEntity.noContent().build();
    }

    private DiaryFluidDTO convertToFluidDTO(DiaryFluid diaryFluid) {
        Fluid fluid = diaryFluid.getFluid();
        double volume = diaryFluid.getVolume();
        double ratio = volume / 100.0;

        return new DiaryFluidDTO(
                diaryFluid.getId(),
                diaryFluid.getDiary().getId(),
                fluid.getId(),
                fluid.getName(),
                volume,
                Math.round(fluid.getCalories() * ratio * 10.0) / 10.0,
                diaryFluid.getCreatedAt(),
                diaryFluid.getUpdatedAt()
        );
    }

    private DiaryFoodDTO convertToFoodDTO(DiaryFood diaryFood) {
        Food food = diaryFood.getFood();
        double weightRatio = diaryFood.getWeight() / 100.0;

        return new DiaryFoodDTO(
                diaryFood.getId(),
                diaryFood.getDiary().getId(),
                food.getId(),
                food.getName(),
                diaryFood.getWeight(),
                Math.round(food.getCalories() * weightRatio * 10.0) / 10.0,
                Math.round(food.getProteins() * weightRatio * 10.0) / 10.0,
                Math.round(food.getFats() * weightRatio * 10.0) / 10.0,
                Math.round(food.getCarbs() * weightRatio * 10.0) / 10.0,
                diaryFood.getCreatedAt(),
                diaryFood.getUpdatedAt()
        );
    }

    @PutMapping("/{diaryId}/food/{foodId}")
    public ResponseEntity<DiaryFoodDTO> updateDiaryFood(
            @PathVariable Long diaryId,
            @PathVariable Long foodId,
            @RequestBody UpdateDiaryFoodRequest request) {
        DiaryFood updatedDiaryFood = diaryService.updateDiaryFood(diaryId, foodId, request);
        return ResponseEntity.ok(convertToFoodDTO(updatedDiaryFood));
    }

    @PutMapping("/{diaryId}/fluid/{fluidId}")
    public ResponseEntity<DiaryFluidDTO> updateDiaryFluid(
            @PathVariable Long diaryId,
            @PathVariable Long fluidId,
            @RequestBody UpdateDiaryFluidRequest request) {
        DiaryFluid updatedDiaryFluid = diaryService.updateDiaryFluid(diaryId, fluidId, request);
        return ResponseEntity.ok(convertToFluidDTO(updatedDiaryFluid));
    }
}