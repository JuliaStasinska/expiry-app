package pl.juliastasinska.expiryapp.templates;

import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.FoodTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.MedicineTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.SubscriptionTemplateDto;

import javax.print.attribute.standard.PresentationDirection;
import java.net.URI;
import java.util.List;
import java.util.concurrent.Flow;

@RestController
@RequestMapping("/templates")
class TemplateController {
    private final TemplateFacade templateFacade;

    TemplateController(final TemplateFacade templateFacade) {
        this.templateFacade = templateFacade;
    }

    @GetMapping("/devices")
    List<DeviceTemplateDto> listDevices(){
        return templateFacade.listDevices();
    }

    @GetMapping("/food")
    List<FoodTemplateDto> listFood(){
        return templateFacade.listFood();
    }

    @GetMapping("/medicines")
    List<MedicineTemplateDto> listMedicines(){
        return templateFacade.listMedicines();
    }

    @GetMapping("/subscriptions")
    List<SubscriptionTemplateDto> listSubscriptions(){
        return templateFacade.listSubscriptions();
    }

    @GetMapping("/devices/{id}")
    ResponseEntity<DeviceTemplateDto> readDevice(@PathVariable int id){
        return templateFacade.readDevice(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/food/{id}")
    ResponseEntity<FoodTemplateDto> readFood(@PathVariable int id){
        return templateFacade.readFood(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/medicines/{id}")
    ResponseEntity<MedicineTemplateDto> readMedicine(@PathVariable int id){
        return templateFacade.readMedicine(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/subscriptions/{id}")
    ResponseEntity<SubscriptionTemplateDto> readSubscription(@PathVariable int id){
        return templateFacade.readSubscription(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/devices")
    ResponseEntity<DeviceTemplateDto> createDevice(@RequestBody DeviceTemplateDto toCreate){
        DeviceTemplateDto result = templateFacade.saveDevice(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PostMapping("/food")
    ResponseEntity<FoodTemplateDto> createFood(@RequestBody FoodTemplateDto toCreate){
        FoodTemplateDto result = templateFacade.saveFood(toCreate);
        return  ResponseEntity.created(URI.create("/"+ result.getId())).body(result);
    }

    @PostMapping("/medicines")
    ResponseEntity<MedicineTemplateDto> createMedicine(@RequestBody MedicineTemplateDto toCreate){
        MedicineTemplateDto result = templateFacade.saveMedicine(toCreate);
        return ResponseEntity.created(URI.create("/"+ result.getId())).body(result);
    }

    @PostMapping("/subscriptions")
    ResponseEntity<SubscriptionTemplateDto> createSubscription(@RequestBody SubscriptionTemplateDto toCreate){
        SubscriptionTemplateDto result = templateFacade.saveSubscription(toCreate);
        return ResponseEntity.created(URI.create("/"+result.getId())).body(result);
    }

    @PutMapping("/devices/{id}")
    ResponseEntity<DeviceTemplateDto> updateDevice(@PathVariable int id, @RequestBody DeviceTemplateDto toUpdate){
        if(id != toUpdate.getId() && toUpdate.getId() != 0){
            throw new IllegalStateException("Path id different than body id: "+ id + "!=" + toUpdate.getId());
        }
        templateFacade.updateDevice(toUpdate,id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/food/{id}")
    ResponseEntity<FoodTemplateDto> updateFood(@PathVariable int id, @RequestBody FoodTemplateDto toUpdate){
        templateFacade.updateFood(toUpdate);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/medicines/{id}")
    ResponseEntity<MedicineTemplateDto> updateMedicine(@PathVariable int id, @RequestBody MedicineTemplateDto toUpdate){
        templateFacade.updateMedicine(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/subscriptions/{id}")
    ResponseEntity<SubscriptionTemplateDto> updateSubscription(@PathVariable int id, @RequestBody SubscriptionTemplateDto toUpdate){
        templateFacade.updateSubscription(toUpdate);
        return ResponseEntity.noContent().build();
    }

    //TODO: add possibility of deleting
    /*@DeleteMapping("/devices/{id}")
    ResponseEntity<DeviceTemplateDto> delete(@PathVariable int id){
        templateFacade.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }*/

    

}
