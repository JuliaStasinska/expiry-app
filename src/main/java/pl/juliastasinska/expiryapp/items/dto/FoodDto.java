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
    private LocalDate useBefore;
    @Enumerated(EnumType.STRING)
    private String storageStatus;
    @Enumerated(EnumType.STRING)
    private String usageStatus;

    FoodDto() {
    }

    FoodDto(int myFoodId, String description, LocalDate expiryDate) {
        this.myFoodId = myFoodId;
        this.description = description;
        this.expiryDate = expiryDate;
    }

    FoodDto(int myFoodId, String description, LocalDate expiryDate, LocalDate useBefore, String storageStatus, String usageStatus) {
        this.myFoodId = myFoodId;
        this.description = description;
        this.expiryDate = expiryDate;
        this.useBefore = useBefore;
        this.storageStatus = storageStatus;
        this.usageStatus = usageStatus;
    }

}
