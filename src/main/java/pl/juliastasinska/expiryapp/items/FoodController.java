package pl.juliastasinska.expiryapp.items;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.juliastasinska.expiryapp.items.dto.FoodDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items/food")
class FoodController {
    private final FoodFacade foodFacade;

    FoodController(final FoodFacade foodFacade) {
        this.foodFacade = foodFacade;
    }

    @GetMapping
    List<FoodDto> listFoods(){
        return foodFacade.listFood();
    }

    @GetMapping(params = "description")
    List<FoodDto> listFoodsWithDescription(@RequestParam String name) {
        return foodFacade.listFoodWithDescription(name);
    }

    @GetMapping(params = "expire")
    List<FoodDto> listFoodToEat(@RequestParam int days) {
        return foodFacade.listFoodToEat(days);
    }

    @PostMapping
    ResponseEntity<FoodDto> createFood(@RequestBody FoodDto toCreate, @RequestParam int FoodTemplateId){
        FoodDto result = foodFacade.saveFood(toCreate, FoodTemplateId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @PutMapping(path = "/{id}", params = "open")
    ResponseEntity<FoodDto> openFood(@PathVariable int id){
        foodFacade.openFood(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", params = "freeze")
    ResponseEntity<FoodDto> freezeFood(@PathVariable int id){
        foodFacade.freezeFood(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", params = "thaw")
    ResponseEntity<FoodDto> thawFood(@PathVariable int id){
        foodFacade.thawFood(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", params = "eat")
    ResponseEntity<FoodDto> eatFood(@PathVariable int id){
        foodFacade.eatFood(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", params = "bin")
    ResponseEntity<FoodDto> binFood(@PathVariable int id){
        foodFacade.binFood(id);
        return ResponseEntity.noContent().build();
    }

    //TODO: add possibility of deleting
    /*@DeleteMapping("/{id}")
    ResponseEntity<FoodDto> delete(@PathVariable int id){
        Facade.deleteFood(id);
        return ResponseEntity.noContent().build();
    }*/

    

}
