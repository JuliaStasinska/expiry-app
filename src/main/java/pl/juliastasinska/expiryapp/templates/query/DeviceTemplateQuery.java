package pl.juliastasinska.expiryapp.templates.query;

import javax.persistence.*;

@Entity
@Table(name = "device_templates")
public class DeviceTemplateQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceId;
    private String name;
    private int daysBetweenCleanings;
    private int daysBetweenPartExchange;

    DeviceTemplateQuery() {
    }

    public DeviceTemplateQuery(int deviceId, String name, int daysBetweenCleanings, int daysBetweenPartExchange){
        this.deviceId = deviceId;
        this.name = name;
        this.daysBetweenCleanings = daysBetweenCleanings;
        this.daysBetweenPartExchange = daysBetweenPartExchange;
    }

    int getDeviceId() { return deviceId; }

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
