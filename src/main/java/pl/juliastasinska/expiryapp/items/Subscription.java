package pl.juliastasinska.expiryapp.items;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.dto.SubscriptionTemplateDto;
import pl.juliastasinska.expiryapp.templates.query.SubscriptionTemplateQuery;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
class Subscription {

    enum SubscriptionStatus{
        TRIAL, REGULAR, SUSPENDED, CANCELLED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mySubscriptionId;
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private SubscriptionTemplateDto subscriptionTemplate;
    private LocalDate renewsOn;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    Subscription() {
    }

    Subscription(String description, SubscriptionTemplateDto subscriptionTemplate, LocalDate renewsOn) {
        this.description = description;
        this.subscriptionTemplate = subscriptionTemplate;
        this.renewsOn = renewsOn;
        this.status = SubscriptionStatus.REGULAR;
    }

    void beginTrial(LocalDate startDate){
        this.status = SubscriptionStatus.TRIAL;
        renewsOn = startDate.plusDays(this.subscriptionTemplate.getDaysTrialPeriod());
    }

    void continueSubscription(LocalDate startDate){
        this.status = SubscriptionStatus.REGULAR;
        renewsOn = startDate.plusDays(this.subscriptionTemplate.getDaysBetweenRenewals());

    }

    void suspendSubscription(LocalDate startDate){
        this.status = SubscriptionStatus.SUSPENDED;
        renewsOn = startDate.plusDays(this.subscriptionTemplate.getMaxDaysOnHold());

    }

    void cancelSubscription(){
        this.status = SubscriptionStatus.CANCELLED;
        renewsOn = null;
    }

}
