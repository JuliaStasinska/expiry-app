package pl.juliastasinska.expiryapp.templates;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;

import javax.persistence.*;

@Entity
@Table(name = "device_templates")
class DeviceTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceId;
    @NotNull
    private String name;
    private int daysBetweenCleanings;
    private int daysBetweenPartExchange;

    DeviceTemplate() {
    }

    DeviceTemplate(String name, int daysBetweenCleanings, int daysBetweenPartExchange) {
        this.name = name;
        this.daysBetweenCleanings = daysBetweenCleanings;
        this.daysBetweenPartExchange = daysBetweenPartExchange;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDaysBetweenCleanings(int daysBetweenCleanings) {
        this.daysBetweenCleanings = daysBetweenCleanings;
    }

    void setDaysBetweenPartExchange(int daysBetweenPartExchange) {
        this.daysBetweenPartExchange = daysBetweenPartExchange;
    }

    DeviceTemplateDto toDto(){
        return new DeviceTemplateDto(deviceId, name, daysBetweenCleanings, daysBetweenPartExchange);
    }
}
