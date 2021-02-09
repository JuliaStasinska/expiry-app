package pl.juliastasinska.expiryapp.items;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.items.dto.FoodDto;
import pl.juliastasinska.expiryapp.templates.dto.FoodTemplateDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "food")
class Food {
    public enum UsageStatus {
        WAITING, EATEN, BINNED
    }

    public enum StorageStatus {
        CLOSED, OPENED, FROZEN, COOKED
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
    private FoodTemplateDto foodTemplate;
    private LocalDate expiryDate;
    private LocalDate useBy;
    @Enumerated(EnumType.STRING)
    private StorageStatus storageStatus;
    @Enumerated(EnumType.STRING)
    private UsageStatus usageStatus;

    Food() {
    }

    Food(String description, FoodTemplateDto foodTemplate, LocalDate expiryDate) {
        this.description = description;
        this.foodTemplate = foodTemplate;
        this.expiryDate = expiryDate;
        this.useBy = expiryDate;
        this.storageStatus = StorageStatus.CLOSED;
        this.usageStatus = UsageStatus.WAITING;
    }

    void open(LocalDate openingDate){
        this.useBy = openingDate.plusDays(foodTemplate.getDaysStoredInFridge());
        this.storageStatus = StorageStatus.OPENED;
    }

    void cook(LocalDate cookingDate){
        this.useBy = cookingDate.plusDays(DEF_FRIDGE_STORAGE);
        this.storageStatus = StorageStatus.COOKED;
    }

    void freeze(LocalDate freezingDate){
        this.useBy = freezingDate.plusDays(DEF_FROZEN_STORAGE);
        this.storageStatus = StorageStatus.FROZEN;
    }

    void thaw(LocalDate defrostingDate){
        this.useBy = defrostingDate.plusDays(DEF_DEFROST_STORAGE);
    }

    void eat(){
        this.useBy = null;
        this.storageStatus = null;
        this.usageStatus = UsageStatus.EATEN;
    }

    void bin(){
        this.useBy = null;
        this.storageStatus = null;
        this.usageStatus = UsageStatus.BINNED;
    }

    FoodDto toDto(){
        return new FoodDto(myFoodId, description, expiryDate, useBy, storageStatus.toString(), usageStatus.toString());
    }

}
