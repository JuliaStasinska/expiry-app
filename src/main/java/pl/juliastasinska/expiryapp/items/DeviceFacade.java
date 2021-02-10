package pl.juliastasinska.expiryapp.items;

import org.springframework.stereotype.Service;
import pl.juliastasinska.expiryapp.items.dto.DeviceDto;
import pl.juliastasinska.expiryapp.templates.*;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;
import pl.juliastasinska.expiryapp.templates.query.DeviceTemplateQuery;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DeviceFacade {

    private final DeviceRepository DeviceRepo;
    private final TemplateFacade templateFacade;

    DeviceFacade(final DeviceRepository DeviceRepo,
                 final TemplateFacade templateFacade) {
        this.DeviceRepo = DeviceRepo;
        this.templateFacade = templateFacade;
    }

    List<DeviceDto> listDevices(){
        return DeviceRepo.findAll().stream()
                .map(Device::toDto)
                .collect(toList());
    }

    List<DeviceDto> listDevicesWithDescription(String nameFragment){
        return DeviceRepo.findByDescriptionContaining(nameFragment).stream()
                .map(Device::toDto)
                .collect(toList());
    }

    List<DeviceDto> listDevicesToClean(int days){
        return DeviceRepo.findByTimeToClean(LocalDate.now().plusDays(days)).stream()
                .map(Device::toDto)
                .collect(toList());
    }

    DeviceDto saveDevice(DeviceDto source, int deviceTemplateId){
        return DeviceRepo.save(new Device(source.getDescription(), templateFacade.readDevice(deviceTemplateId).get(), source.getBuyingDate())).toDto();
    }

    DeviceDto cleanDevice(int myDeviceId){
        return DeviceRepo.save(DeviceRepo.findById(myDeviceId).map(existingDevice -> {
            existingDevice.cleanDevice(LocalDate.now());
            return existingDevice;
        }).orElseThrow(()->new IllegalArgumentException("Device with a given id not found")))
                .toDto();
    }

    DeviceDto serviceDevice(int myDeviceId){
        return DeviceRepo.save(DeviceRepo.findById(myDeviceId).map(existingDevice -> {
            existingDevice.changePart(LocalDate.now());
            return existingDevice;
        }).orElseThrow(()->new IllegalArgumentException("Device with a given id not found")))
                .toDto();
    }

    void deleteDevice(int myDeviceId){
        DeviceRepo.deleteById(myDeviceId);
    }

}
