package pl.juliastasinska.expiryapp.items;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.juliastasinska.expiryapp.items.dto.DeviceDto;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/items/devices")
class DeviceController {
    private final DeviceFacade deviceFacade;

    DeviceController(final DeviceFacade deviceFacade) {
        this.deviceFacade = deviceFacade;
    }

    @GetMapping
    List<DeviceDto> listDevices(){
        return deviceFacade.listDevices();
    }

    @GetMapping(params = "description")
    List<DeviceDto> listDevicesWithDescription(@RequestParam String name) {
        return deviceFacade.listDevicesWithDescription(name);
    }

    @GetMapping(params = "clean")
    List<DeviceDto> listDevicesToClean(@RequestParam int days) {
        return deviceFacade.listDevicesToClean(days);
    }

    @PostMapping
    ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto toCreate, @RequestParam int deviceTemplateId){
        DeviceDto result = deviceFacade.saveDevice(toCreate, deviceTemplateId);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    @PutMapping(path = "/{id}", params = "clean")
    ResponseEntity<DeviceDto> cleanDevice(@PathVariable int id){
        deviceFacade.cleanDevice(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}", params = "service")
    ResponseEntity<DeviceDto> serviceDevice(@PathVariable int id){
        deviceFacade.serviceDevice(id);
        return ResponseEntity.noContent().build();
    }

    //TODO: add possibility of deleting
    /*@DeleteMapping("/{id}")
    ResponseEntity<DeviceDto> delete(@PathVariable int id){
        Facade.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }*/

    

}
