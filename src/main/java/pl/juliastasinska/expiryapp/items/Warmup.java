package pl.juliastasinska.expiryapp.items;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.juliastasinska.expiryapp.templates.*;

import java.time.LocalDate;

@Component("itemWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final DeviceRepository DeviceRepo;
    private final FoodRepository FoodRepo;
    private final MedicineRepository MedicineRepo;
    private final SubscriptionRepository SubscriptionRepo;
    private final TemplateFacade templateFacade;

    Warmup(final DeviceRepository DRepo, final FoodRepository FRepo, final MedicineRepository MRepo, final SubscriptionRepository SRepo, TemplateFacade templateFacade) {
        this.DeviceRepo = DRepo;
        this.FoodRepo = FRepo;
        this.MedicineRepo = MRepo;
        this.SubscriptionRepo = SRepo;
        this.templateFacade = templateFacade;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (DeviceRepo.count() == 0) {
            var device = new Device("Electric toothbrush", null, LocalDate.of(2020,11,19));
            DeviceRepo.save(device);
        }

        if (FoodRepo.count() == 0) {
            var food = new Food("Example food", null ,LocalDate.of(2021,2,9));
            FoodRepo.save(food);
        }

        if (MedicineRepo.count() == 0) {
            var medicine = new Medicine("Example medicine", null ,LocalDate.of(2021,2,9));
            MedicineRepo.save(medicine);
        }

        if (SubscriptionRepo.count() == 0) {
            var subscription = new Subscription("Example subscription", null,LocalDate.of(2021,2,9));
            SubscriptionRepo.save(subscription);
        }
    }
}
