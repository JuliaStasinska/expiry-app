package pl.juliastasinska.expiryapp.templates;

import org.springframework.stereotype.Service;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.FoodTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.MedicineTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.SubscriptionTemplateDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class TemplateFacade {
    private final DeviceTemplateRepository DTRepo;
    private final FoodTemplateRepository FTRepo;
    private final MedicineTemplateRepository MTRepo;
    private final SubscriptionTemplateRepository STRepo;

    TemplateFacade(final DeviceTemplateRepository DTRepo, final FoodTemplateRepository FTRepo, final MedicineTemplateRepository MTRepo, final SubscriptionTemplateRepository STRepo) {
        this.DTRepo = DTRepo;
        this.FTRepo = FTRepo;
        this.MTRepo = MTRepo;
        this.STRepo = STRepo;
    }

    List<DeviceTemplateDto> listDevices(){
        return DTRepo.findAll().stream()
                .map(DeviceTemplate::toDto)
                .collect(toList());
    }

    List<FoodTemplateDto> listFood(){
        return FTRepo.findAll().stream()
                .map(FoodTemplate::toDto)
                .collect(toList());
    }

    List<MedicineTemplateDto> listMedicines(){
        return MTRepo.findAll().stream()
                .map(MedicineTemplate::toDto)
                .collect(Collectors.toList());
    }

    List<SubscriptionTemplateDto> listSubscriptions(){
        return STRepo.findAll().stream()
                .map(SubscriptionTemplate::toDto)
                .collect(Collectors.toList());
    }

    public Optional<DeviceTemplateDto> readDevice(int id){
        return DTRepo.findById(id).map(DeviceTemplate::toDto);
    }

    public Optional<FoodTemplateDto> readFood(int id){
        return FTRepo.findById(id).map(FoodTemplate::toDto);
    }

    public Optional<MedicineTemplateDto> readMedicine(int id){
        return MTRepo.findById(id).map(MedicineTemplate::toDto);
    }

    public Optional<SubscriptionTemplateDto> readSubscription(int id){
        return STRepo.findById(id).map(SubscriptionTemplate::toDto);
    }

    DeviceTemplateDto saveDevice(DeviceTemplateDto source){
        return DTRepo.save(new DeviceTemplate(
                source.getName(),
                source.getDaysBetweenCleanings(),
                source.getDaysBetweenPartExchange())
        ).toDto();
    }

    FoodTemplateDto saveFood(FoodTemplateDto source){
        return FTRepo.save(
                new FoodTemplate(
                        source.getName(),
                        source.getCategory(),
                        source.getDaysStoredRoomTemperature(),
                        source.getDaysStoredInFridge(),
                        source.getDaysStoredInFreezer())
        ).toDto();
    }

    MedicineTemplateDto saveMedicine(MedicineTemplateDto source){
        return MTRepo.save(
                new MedicineTemplate(
                        source.getName(),
                        source.getDaysStored()
                )
        ).toDto();
    }

    SubscriptionTemplateDto saveSubscription(SubscriptionTemplateDto source){
        return  STRepo.save(
                new SubscriptionTemplate(
                        source.getName(),
                        source.getDaysTrialPeriod(),
                        source.getDaysBetweenRenewals(),
                        source.getMaxDaysOnHold()
                )
        ).toDto();
    }

    DeviceTemplateDto updateDevice(DeviceTemplateDto source, int id){
        return DTRepo.save(DTRepo.findById(id).map(existingDevice-> {
            existingDevice.setDaysBetweenCleanings(source.getDaysBetweenCleanings());
            existingDevice.setDaysBetweenPartExchange(source.getDaysBetweenPartExchange());
            return existingDevice;
        }).orElseThrow(()->new IllegalArgumentException("Device template with a given id not found"))).toDto();
    }

    FoodTemplateDto updateFood(FoodTemplateDto source){
        return FTRepo.save(FTRepo.findById(source.getId()).map(existingFood-> {
            existingFood.setCategory(source.getCategory() != null ? FoodCategory.valueOf(source.getCategory()) : FoodCategory.GENERIC);
            existingFood.setDaysStoredInFridge(source.getDaysStoredInFridge());
            existingFood.setDaysStoredInFreezer(source.getDaysStoredInFreezer());
            existingFood.setDaysStoredRoomTemperature(source.getDaysStoredRoomTemperature());
            return existingFood;
        }).orElseThrow(()->new IllegalArgumentException("Food template with a given id not found"))).toDto();
    }

    MedicineTemplateDto updateMedicine(MedicineTemplateDto source){
        return MTRepo.save(MTRepo.findById(source.getId()).map(existingMedicine-> {
            existingMedicine.setDaysStored(source.getDaysStored());
            return existingMedicine;
        }).orElseThrow(()->new IllegalArgumentException("Medicine template with a given id not found"))).toDto();
    }

    SubscriptionTemplateDto updateSubscription(SubscriptionTemplateDto source){
        return STRepo.save(STRepo.findById(source.getId()).map(existingSubscription-> {
            existingSubscription.setDaysTrialPeriod(source.getDaysTrialPeriod());
            existingSubscription.setMaxDaysOnHold(source.getMaxDaysOnHold());
            existingSubscription.setDaysBetweenRenewals(source.getDaysBetweenRenewals());
            return existingSubscription;
        }).orElseThrow(()->new IllegalArgumentException("Subscription template with a given id not found"))).toDto();
    }

}
