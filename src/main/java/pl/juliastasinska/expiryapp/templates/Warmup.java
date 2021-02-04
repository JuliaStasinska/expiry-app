package pl.juliastasinska.expiryapp.templates;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component("templateWarmup")
class Warmup implements ApplicationListener<ContextRefreshedEvent> {
    private final DeviceTemplateRepository DTRepo;
    private final FoodTemplateRepository FTRepo;
    private final MedicineTemplateRepository MTRepo;
    private final SubscriptionTemplateRepository STRepo;

    Warmup(final DeviceTemplateRepository DTRepo, final FoodTemplateRepository FTRepo, final MedicineTemplateRepository MTRepo, final SubscriptionTemplateRepository STRepo) {
        this.DTRepo = DTRepo;
        this.FTRepo = FTRepo;
        this.MTRepo = MTRepo;
        this.STRepo = STRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (DTRepo.count() == 0) {
            var device = new DeviceTemplate("Example device",0,0);
            DTRepo.save(device);
        }

        if (FTRepo.count() == 0) {
            var food = new FoodTemplate("Example food", "GENERIC",0,0,0);
            FTRepo.save(food);
        }

        if (MTRepo.count() == 0) {
            var medicine = new MedicineTemplate("Example medicine",0);
            MTRepo.save(medicine);
        }

        if (STRepo.count() == 0) {
            var subscription = new SubscriptionTemplate("Example subscription",0,0,0);
            STRepo.save(subscription);
        }
    }
}
