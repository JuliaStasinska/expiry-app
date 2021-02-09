package pl.juliastasinska.expiryapp.templates.dto;

import javax.persistence.*;

@Entity
@Table(name = "device_templates")
public class DeviceTemplateDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceId;
    private String name;
    private int daysBetweenCleanings;
    private int daysBetweenPartExchange;

    public DeviceTemplateDto(){
    }

    public DeviceTemplateDto(final int deviceId, final String name, final int daysBetweenCleanings, final int daysBetweenPartExchange){
        this.deviceId = deviceId;
        this.name = name;
        this.daysBetweenCleanings = daysBetweenCleanings;
        this.daysBetweenPartExchange = daysBetweenPartExchange;
    }

    public int getId() {
        return deviceId;
    }

    public String getName() {
        return name;
    }

    public int getDaysBetweenCleanings() {
        return daysBetweenCleanings;
    }

    public int getDaysBetweenPartExchange() {
        return daysBetweenPartExchange;
    }
}
