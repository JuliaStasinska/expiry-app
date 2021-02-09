package pl.juliastasinska.expiryapp.templates.query;

import javax.persistence.*;


public class DeviceTemplateQuery {

    private int deviceId;
    private String name;
    //private int daysBetweenCleanings;
    //private int daysBetweenPartExchange;

    DeviceTemplateQuery() {
    }

    public DeviceTemplateQuery(int deviceId, String name){
        this.deviceId = deviceId;
        this.name = name;
        //this.daysBetweenCleanings = daysBetweenCleanings;
        //this.daysBetweenPartExchange = daysBetweenPartExchange;
    }

    int getDeviceId() { return deviceId; }

    public String getName() {
        return name;
    }

    /*public int getDaysBetweenCleanings() {
        return daysBetweenCleanings;
    }

    public int getDaysBetweenPartExchange() {
        return daysBetweenPartExchange;
    }*/
}
