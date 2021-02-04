package pl.juliastasinska.expiryapp.items;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.query.FoodTemplateQuery;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Table(name = "food")
class Food {
    enum StorageStatus{
        CLOSED,OPENED,FROZEN,COOKED
    }

    enum UsageStatus{
        WAITING,EATEN,BINNED
    }

    @Transient
    private final int DEF_FRIDGE_STORAGE=3;
    @Transient
    private final int DEF_FROZEN_STORAGE=60;
    @Transient
    private final int DEF_DEFROST_STORAGE=2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myFoodId;
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodTemplateQuery food;
    private LocalDate expiryDate;
    private LocalDate useBefore;
    @Enumerated(EnumType.STRING)
    private StorageStatus storageStatus;
    @Enumerated(EnumType.STRING)
    private UsageStatus usageStatus;

    Food() {
    }

    Food(String description, FoodTemplateQuery food, LocalDate expiryDate) {
        this.description = description;
        this.food = food;
        this.expiryDate = expiryDate;
        this.useBefore = expiryDate;
        this.storageStatus = StorageStatus.CLOSED;
        this.usageStatus = UsageStatus.WAITING;
    }

    void open(LocalDate openingDate){
        this.useBefore = openingDate.plusDays(food.getDaysStoredInFridge());
        this.storageStatus = StorageStatus.OPENED;
    }

    void cook(LocalDate cookingDate){
        this.useBefore = cookingDate.plusDays(DEF_FRIDGE_STORAGE);
        this.storageStatus = StorageStatus.COOKED;
    }

    void freeze(LocalDate freezingDate){
        this.useBefore = freezingDate.plusDays(DEF_FROZEN_STORAGE);
        this.storageStatus = StorageStatus.FROZEN;
    }

    void defrost(LocalDate defrostingDate){
        this.useBefore = defrostingDate.plusDays(DEF_DEFROST_STORAGE);
    }

    void eat(){
        this.useBefore = null;
        this.storageStatus = null;
        this.usageStatus = UsageStatus.EATEN;
    }

    void bin(){
        this.useBefore = null;
        this.storageStatus = null;
        this.usageStatus = UsageStatus.BINNED;
    }
}
