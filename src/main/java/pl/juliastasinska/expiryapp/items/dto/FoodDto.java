package pl.juliastasinska.expiryapp.items.dto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "food")
public class FoodDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myFoodId;
    private String description;
    private LocalDate expiryDate;
    private LocalDate useBy;
    private String storageStatus;
    private String usageStatus;

    public FoodDto() {
    }

    public FoodDto(int myFoodId, String description, LocalDate expiryDate) {
        this.myFoodId = myFoodId;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    public FoodDto(int myFoodId, String description, LocalDate expiryDate, LocalDate useBy, String storageStatus, String usageStatus) {
        this.myFoodId = myFoodId;
        this.description = description;
        this.expiryDate = expiryDate;
        this.useBy = useBy;
        this.storageStatus = storageStatus;
        this.usageStatus = usageStatus;
    }

    public int getId() {
        return myFoodId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public LocalDate getUseBy() {
        return useBy;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

    public String getUsageStatus() {
        return usageStatus;
    }

}
