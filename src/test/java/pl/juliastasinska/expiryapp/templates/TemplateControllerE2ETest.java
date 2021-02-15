package pl.juliastasinska.expiryapp.templates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.test.context.ActiveProfiles;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;
import pl.juliastasinska.expiryapp.templates.dto.FoodTemplateDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("e2e")
class TemplateControllerE2ETest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    DeviceTemplateRepository dtRepo;
    @Autowired
    FoodTemplateRepository ftRepo;
    @Autowired
    MedicineTemplateRepository mtRepo;
    @Autowired
    SubscriptionTemplateRepository stRepo;

    TemplateControllerE2ETest(){
        new Warmup(dtRepo,ftRepo,mtRepo,stRepo);
    }

    @Test
    void httpGet_returnsAllDeviceTemplates(){
        //given
        int initialSize= dtRepo.findAll().size();
        dtRepo.save(new DeviceTemplate("foo",30,365));
        dtRepo.save(new DeviceTemplate("bar",14,90));
        //when
        DeviceTemplateDto[] dTemplates=restTemplate.getForObject("http://localhost:"+port+"/templates/devices", DeviceTemplateDto[].class);
        //then
        assertThat(dTemplates).hasSize(initialSize+2);
    }

    @Test
    void httpGet_returnsDeviceTemplateWithId(){
        //given
        dtRepo.save(new DeviceTemplate("foo",30,365));
        DeviceTemplateDto toGet=dtRepo.findAll().stream().map(DeviceTemplate::toDto).findAny().get();
        //when
        DeviceTemplateDto got = restTemplate.getForObject("http://localhost:"+port+"/templates/devices/"+toGet.getId(), DeviceTemplateDto.class);

        //then
        assertThat(got).hasFieldOrPropertyWithValue("name",toGet.getName());
        assertThat(got).hasFieldOrPropertyWithValue("daysBetweenPartExchange",toGet.getDaysBetweenPartExchange());
        assertThat(got).hasFieldOrPropertyWithValue("daysBetweenCleanings",toGet.getDaysBetweenCleanings());
    }

    @Test
    void httpGet_returnsAllFoodTemplates(){
        //given
        int initialSize= ftRepo.findAll().size();
        ftRepo.save(new FoodTemplate("foo","GENERIC",30,60,90));
        ftRepo.save(new FoodTemplate("bar","GENERIC",0,4,30));
        //when
        FoodTemplateDto[] fTemplates=restTemplate.getForObject("http://localhost:"+port+"/templates/food", FoodTemplateDto[].class);
        //then
        assertThat(fTemplates).hasSize(initialSize+2);
    }

    @Test
    void httpGet_returnsFoodTemplateWithFood(){
        //given
        ftRepo.save(new FoodTemplate("foo","GENERIC",1,30,365));
        FoodTemplateDto toGet=ftRepo.findAll().stream().map(FoodTemplate::toDto).findAny().get();
        //when
        FoodTemplateDto got = restTemplate.getForObject("http://localhost:"+port+"/templates/food/"+toGet.getId(), FoodTemplateDto.class);

        //then
        assertThat(got).hasFieldOrPropertyWithValue("name",toGet.getName());
        assertThat(got).hasFieldOrPropertyWithValue("category",toGet.getCategory());
        assertThat(got).hasFieldOrPropertyWithValue("daysStoredInFridge",toGet.getDaysStoredInFridge());
    }



}