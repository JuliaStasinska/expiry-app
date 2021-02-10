package pl.juliastasinska.expiryapp.items;

import org.springframework.stereotype.Service;
import pl.juliastasinska.expiryapp.items.dto.DeviceDto;
import pl.juliastasinska.expiryapp.items.dto.FoodDto;
import pl.juliastasinska.expiryapp.templates.TemplateFacade;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class FoodFacade {

    private final FoodRepository FoodRepo;
    private final TemplateFacade templateFacade;

    FoodFacade(final FoodRepository FoodRepo,
               final TemplateFacade templateFacade) {
        this.FoodRepo = FoodRepo;
        this.templateFacade = templateFacade;
    }

    List<FoodDto> listFood(){
        return FoodRepo.findAll().stream()
                .map(Food::toDto)
                .collect(toList());
    }

    List<FoodDto> listFoodWithDescription(String nameFragment){
        return FoodRepo.findByDescriptionContaining(nameFragment).stream()
                .map(Food::toDto)
                .collect(toList());
    }

    List<FoodDto> listFoodToEat(int days){
        return FoodRepo.findByUseBy(LocalDate.now().plusDays(days)).stream()
                .map(Food::toDto)
                .collect(toList());
    }

    FoodDto saveFood(FoodDto source, int foodTemplateId){
        return FoodRepo.save(new Food(source.getDescription(), templateFacade.readFood(foodTemplateId).get(), source.getExpiryDate())).toDto();
    }

    FoodDto openFood(int myFoodId){
        return FoodRepo.save(FoodRepo.findById(myFoodId).map(existingFood -> {
            existingFood.open(LocalDate.now());
            return existingFood;
        }).orElseThrow(()->new IllegalArgumentException("Food with a given id not found")))
                .toDto();
    }

    FoodDto cookFood(int myFoodId){
        return FoodRepo.save(FoodRepo.findById(myFoodId).map(existingFood -> {
            existingFood.cook(LocalDate.now());
            return existingFood;
        }).orElseThrow(()->new IllegalArgumentException("Food with a given id not found")))
                .toDto();
    }

    FoodDto freezeFood(int myFoodId){
        return FoodRepo.save(FoodRepo.findById(myFoodId).map(existingFood -> {
            existingFood.freeze(LocalDate.now());
            return existingFood;
        }).orElseThrow(()->new IllegalArgumentException("Food with a given id not found")))
                .toDto();
    }

    FoodDto thawFood(int myFoodId){
        return FoodRepo.save(FoodRepo.findById(myFoodId).map(existingFood -> {
            existingFood.thaw(LocalDate.now());
            return existingFood;
        }).orElseThrow(()->new IllegalArgumentException("Food with a given id not found")))
                .toDto();
    }

    FoodDto eatFood(int myFoodId){
        return FoodRepo.save(FoodRepo.findById(myFoodId).map(existingFood -> {
            existingFood.eat();
            return existingFood;
        }).orElseThrow(()->new IllegalArgumentException("Food with a given id not found")))
                .toDto();
    }

    FoodDto binFood(int myFoodId){
        return FoodRepo.save(FoodRepo.findById(myFoodId).map(existingFood -> {
            existingFood.bin();
            return existingFood;
        }).orElseThrow(()->new IllegalArgumentException("Food with a given id not found")))
                .toDto();
    }

    void deleteFood(int myFoodId){
        FoodRepo.deleteById(myFoodId);
    }


}
