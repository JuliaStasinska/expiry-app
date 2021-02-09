package pl.juliastasinska.expiryapp.items;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.items.dto.DeviceDto;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;
import pl.juliastasinska.expiryapp.templates.query.DeviceTemplateQuery;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "devices")
class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myDeviceId;
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private DeviceTemplateDto deviceTemplate;
    private LocalDate buyingDate;
    private LocalDate timeToChangePart;
    private LocalDate timeToClean;
    private boolean partChanged;
    private boolean cleaned;

    Device() {
    }

    Device(String description, DeviceTemplateDto deviceTemplate, LocalDate buyingDate) {
        this.description = description;
        this.deviceTemplate = deviceTemplate;
        this.buyingDate = buyingDate;
        this.timeToChangePart = buyingDate.plusDays(deviceTemplate!=null ? deviceTemplate.getDaysBetweenPartExchange():0);
        this.timeToClean = buyingDate.plusDays(deviceTemplate!=null ? deviceTemplate.getDaysBetweenCleanings():0);
        this.partChanged = true;
        this.cleaned = true;
    }

    void cleanDevice(LocalDate cleaningDate){
        this.timeToClean = cleaningDate.plusDays(this.deviceTemplate.getDaysBetweenCleanings());
        this.cleaned = true;
    }

    void changePart(LocalDate serviceTime){
        this.timeToChangePart = serviceTime.plusDays(this.deviceTemplate.getDaysBetweenPartExchange());
        this.partChanged = true;
    }

    DeviceDto toDto(){
        return new DeviceDto(myDeviceId, description, buyingDate, timeToChangePart, timeToClean, partChanged, cleaned);
    }

}
