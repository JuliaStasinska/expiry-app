package pl.juliastasinska.expiryapp.templates;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.dto.SubscriptionTemplateDto;

import javax.persistence.*;

@Entity
@Table(name = "subscription_templates")
class SubscriptionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriptionId;
    @NotNull
    private String name;
    private int daysTrialPeriod;
    private int daysBetweenRenewals;
    private int maxDaysOnHold;

    SubscriptionTemplate() {
    }

    SubscriptionTemplate(String name, int daysTrialPeriod, int daysBetweenRenewals, int maxDaysOnHold) {
        this.name = name;
        this.daysTrialPeriod = daysTrialPeriod;
        this.daysBetweenRenewals = daysBetweenRenewals;
        this.maxDaysOnHold = maxDaysOnHold;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDaysTrialPeriod(int daysTrialPeriod) {
        this.daysTrialPeriod = daysTrialPeriod;
    }

    void setDaysBetweenRenewals(int daysBetweenRenewals) {
        this.daysBetweenRenewals = daysBetweenRenewals;
    }

    void setMaxDaysOnHold(int maxDaysOnHold) {
        this.maxDaysOnHold = maxDaysOnHold;
    }

    SubscriptionTemplateDto toDto(){
        return new SubscriptionTemplateDto(subscriptionId, name, daysTrialPeriod, daysBetweenRenewals, maxDaysOnHold);
    }
}
