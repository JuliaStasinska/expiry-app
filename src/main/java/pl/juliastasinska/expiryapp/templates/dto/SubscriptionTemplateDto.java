package pl.juliastasinska.expiryapp.templates.dto;

import javax.persistence.*;

@Entity
@Table(name = "subscription_templates")
public class SubscriptionTemplateDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriptionId;
    private String name;
    private int daysTrialPeriod;
    private int daysBetweenRenewals;
    private int maxDaysOnHold;

    public SubscriptionTemplateDto(int subscriptionId, String name, int daysTrialPeriod, int daysBetweenRenewals, int maxDaysOnHold) {
        this.subscriptionId = subscriptionId;
        this.name = name;
        this.daysTrialPeriod = daysTrialPeriod;
        this.daysBetweenRenewals = daysBetweenRenewals;
        this.maxDaysOnHold = maxDaysOnHold;
    }

    public int getId() {
        return subscriptionId;
    }

    public String getName() {
        return name;
    }

    public int getDaysTrialPeriod() {
        return daysTrialPeriod;
    }

    public int getDaysBetweenRenewals() {
        return daysBetweenRenewals;
    }

    public int getMaxDaysOnHold() {
        return maxDaysOnHold;
    }
}
