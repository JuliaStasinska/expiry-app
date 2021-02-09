package pl.juliastasinska.expiryapp.items;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.juliastasinska.expiryapp.items.dto.DeviceDto;
import pl.juliastasinska.expiryapp.templates.TemplateFacade;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.FoodTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.MedicineTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.SubscriptionTemplateDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items")
class ItemController {
    private final ItemFacade itemFacade;

    ItemController(final ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    @GetMapping("/devices")
    List<DeviceDto> listDevices(){
        return itemFacade.listDevices();
    }

/*    todo: finish other item types

    @GetMapping("/food")
    List<FoodDto> listFood(){
        return itemFacade.listFood();
    }

    @GetMapping("/medicines")
    List<MedicineDto> listMedicines(){
        return itemFacade.listMedicines();
    }

    @GetMapping("/subscriptions")
    List<SubscriptionDto> listSubscriptions(){
        return itemFacade.listSubscriptions();
    }*/

    @PostMapping(path = "/devices")
    ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto toCreate, @RequestParam int deviceTemplateId){
        DeviceDto result = itemFacade.saveDevice(toCreate, deviceTemplateId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    //todo: finish for other item types
    /*@PostMapping("/food")
    ResponseEntity<FoodDto> createFood(@RequestBody FoodDto toCreate){
        FoodDto result = itemFacade.saveFood(toCreate);
        return  ResponseEntity.created(URI.create("/"+ result.getId())).body(result);
    }

    @PostMapping("/medicines")
    ResponseEntity<MedicineDto> createMedicine(@RequestBody MedicineDto toCreate){
        MedicineDto result = itemFacade.saveMedicine(toCreate);
        return ResponseEntity.created(URI.create("/"+ result.getId())).body(result);
    }

    @PostMapping("/subscriptions")
    ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto toCreate){
        SubscriptionDto result = itemFacade.saveSubscription(toCreate);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }*/

    @PutMapping(path = "/devices/{id}", params = "clean")
    ResponseEntity<DeviceDto> cleanDevice(@PathVariable int id){
        itemFacade.cleanDevice(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/devices/{id}", params = "service")
    ResponseEntity<DeviceDto> serviceDevice(@PathVariable int id){
        itemFacade.serviceDevice(id);
        return ResponseEntity.noContent().build();
    }


    //todo: add other items
    /*@PutMapping("/food/{id}")
    ResponseEntity<FoodDto> updateFood(@PathVariable int id, @RequestBody FoodDto toUpdate){
        itemFacade.updateFood(toUpdate);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/medicines/{id}")
    ResponseEntity<MedicineDto> updateMedicine(@PathVariable int id, @RequestBody MedicineDto toUpdate){
        itemFacade.updateMedicine(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/subscriptions/{id}")
    ResponseEntity<SubscriptionDto> updateSubscription(@PathVariable int id, @RequestBody SubscriptionDto toUpdate){
        itemFacade.updateSubscription(toUpdate);
        return ResponseEntity.noContent().build();
    }*/

    //TODO: add possibility of deleting
    /*@DeleteMapping("/devices/{id}")
    ResponseEntity<DeviceDto> delete(@PathVariable int id){
        Facade.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }*/

    

}
