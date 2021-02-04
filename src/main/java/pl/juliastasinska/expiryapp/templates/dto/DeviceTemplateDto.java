package pl.juliastasinska.expiryapp.templates.dto;

public class DeviceTemplateDto {

    private int deviceId;
    private String name;
    private int daysBetweenCleanings;
    private int daysBetweenPartExchange;

    public DeviceTemplateDto(int deviceId, String name, int daysBetweenCleanings, int daysBetweenPartExchange){
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
