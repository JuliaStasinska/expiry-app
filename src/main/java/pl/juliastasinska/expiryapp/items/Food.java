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
        CLOSED, OPENED, FROZEN, THAWED, COOKED
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
        if(UsageStatus.EATEN==usageStatus || UsageStatus.BINNED==usageStatus){
            throw new IllegalStateException("Food is not available, it has been eaten or binned.");
        }
        this.useBy = openingDate.plusDays(foodTemplate.getDaysStoredInFridge());
        this.storageStatus = StorageStatus.OPENED;
    }

    void cook(LocalDate cookingDate){
        if(UsageStatus.EATEN==usageStatus || UsageStatus.BINNED==usageStatus){
            throw new IllegalStateException("Food is not available, it has been eaten or binned.");
        }
        this.useBy = cookingDate.plusDays(foodTemplate.getDaysStoredInFridge());
        this.storageStatus = StorageStatus.COOKED;
    }

    void freeze(LocalDate freezingDate){
        if(UsageStatus.EATEN==usageStatus || UsageStatus.BINNED==usageStatus){
            throw new IllegalStateException("Food is not available, it has been eaten or binned.");
        }
        if(StorageStatus.FROZEN==storageStatus){
            throw new IllegalStateException("Food is already frozen");
        }
        System.out.println("days stored in freezer: "+foodTemplate.getDaysStoredInFreezer());
        this.useBy = freezingDate.plusDays(foodTemplate.getDaysStoredInFreezer());
        this.storageStatus = StorageStatus.FROZEN;
    }

    void thaw(LocalDate defrostingDate){
        if(UsageStatus.EATEN==usageStatus || UsageStatus.BINNED==usageStatus){
            throw new IllegalStateException("Food is not available, it has been eaten or binned.");
        }
        if(StorageStatus.FROZEN!=storageStatus){
            throw new IllegalStateException("Food is not frozen, it cannot be thawed");
        }
        this.useBy = defrostingDate.plusDays(DEF_DEFROST_STORAGE);
        this.storageStatus=StorageStatus.THAWED;
    }

    void eat(){
        if(UsageStatus.EATEN==usageStatus || UsageStatus.BINNED==usageStatus){
            throw new IllegalStateException("Food is not available, it has been eaten or binned.");
        }
        this.useBy = null;
        this.storageStatus = null;
        this.usageStatus = UsageStatus.EATEN;
    }

    void bin(){
        if(UsageStatus.EATEN==usageStatus || UsageStatus.BINNED==usageStatus){
            throw new IllegalStateException("Food is not available, it has been eaten or binned.");
        }
        this.useBy = null;
        this.storageStatus = null;
        this.usageStatus = UsageStatus.BINNED;
    }

    FoodDto toDto(){
        return new FoodDto(myFoodId, description, expiryDate, useBy, storageStatus!=null?storageStatus.toString():null, usageStatus!=null?usageStatus.toString():null);
    }

}
