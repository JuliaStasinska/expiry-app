package pl.juliastasinska.expiryapp.items.dto;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;
import pl.juliastasinska.expiryapp.templates.query.DeviceTemplateQuery;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "devices")
public class DeviceDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myDeviceId;
    private String description;
    private LocalDate buyingDate;
    private LocalDate timeToChangePart;
    private LocalDate timeToClean;
    private boolean partChanged;
    private boolean cleaned;

    public DeviceDto(){
    }

    public DeviceDto(int myDeviceId,
                     String description,
                     ZonedDateTime buyingDate){
        this.myDeviceId=myDeviceId;
        this.description = description;
        this.buyingDate = LocalDate.of(buyingDate.getYear(), buyingDate.getMonth(), buyingDate.getDayOfMonth());
    }

    public DeviceDto(int myDeviceId,
              String description,
              LocalDate buyingDate,
              LocalDate timeToChangePart,
              LocalDate timeToClean,
              boolean partChanged,
              boolean cleaned) {
        this.myDeviceId=myDeviceId;
        this.description = description;
        this.buyingDate = buyingDate;
        this.timeToChangePart=timeToChangePart;
        this.timeToClean=timeToClean;
        this.partChanged = partChanged;
        this.cleaned = cleaned;
    }

    public int getId(){
        return myDeviceId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getBuyingDate() {
        return buyingDate;
    }

    public LocalDate getTimeToChangePart() {
        return timeToChangePart;
    }

    public LocalDate getTimeToClean() {
        return timeToClean;
    }

    public boolean isPartChanged() {
        return partChanged;
    }

    public boolean isCleaned() {
        return cleaned;
    }
}
