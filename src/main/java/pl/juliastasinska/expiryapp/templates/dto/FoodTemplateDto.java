package pl.juliastasinska.expiryapp.templates.dto;

import pl.juliastasinska.expiryapp.templates.FoodCategory;

import javax.persistence.*;

@Entity
@Table(name = "food_templates")
public class FoodTemplateDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;
    private String name;
    private String category;
    private int daysStoredRoomTemperature;
    private int daysStoredInFridge;
    private int daysStoredInFreezer;

    public FoodTemplateDto(){
    }

    public FoodTemplateDto(int foodId, String name, String category, int daysStoredRoomTemperature, int daysStoredInFridge, int daysStoredInFreezer) {
        this.foodId = foodId;
        this.name = name;
        this.category = category;
        this.daysStoredRoomTemperature = daysStoredRoomTemperature;
        this.daysStoredInFridge = daysStoredInFridge;
        this.daysStoredInFreezer = daysStoredInFreezer;
    }

    public int getId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getDaysStoredRoomTemperature() {
        return daysStoredRoomTemperature;
    }

    public int getDaysStoredInFridge() {
        return daysStoredInFridge;
    }

    public int getDaysStoredInFreezer() {
        return daysStoredInFreezer;
    }
}
