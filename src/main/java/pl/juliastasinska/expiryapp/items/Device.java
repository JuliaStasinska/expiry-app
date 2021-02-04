package pl.juliastasinska.expiryapp.items;

import com.sun.istack.NotNull;
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
    private DeviceTemplateQuery device;
    private LocalDate buyingDate;
    private LocalDate timeToChangePart;
    private LocalDate timeToClean;
    private boolean partChanged;
    private boolean cleaned;

    Device() {
    }

    Device(String description, DeviceTemplateQuery device, LocalDate buyingDate) {
        this.description = description;
        this.device = device;
        this.buyingDate = buyingDate;
        this.timeToChangePart = buyingDate.plusDays(device.getDaysBetweenPartExchange());
        this.timeToClean = buyingDate.plusDays(device.getDaysBetweenCleanings());
        this.partChanged = true;
        this.cleaned = true;
    }

    void cleanDevice(LocalDate cleaningDate){
        this.timeToClean = cleaningDate.plusDays(this.device.getDaysBetweenCleanings());
        this.cleaned = true;
    }

    void changePart(LocalDate serviceTime){
        this.timeToChangePart = serviceTime.plusDays(this.device.getDaysBetweenPartExchange());
        this.partChanged = true;
    }

}
