package pl.juliastasinska.expiryapp.templates.query;

import pl.juliastasinska.expiryapp.templates.FoodCategory;

import javax.persistence.*;

@Entity
@Table(name = "food_templates")
public class FoodTemplateQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;
    private String name;
    @Transient
    private FoodCategory category;
    private int daysStoredRoomTemperature;
    private int daysStoredInFridge;
    private int daysStoredInFreezer;

    FoodTemplateQuery() {
    }

    public FoodTemplateQuery(int foodId, String name, FoodCategory category, int daysStoredRoomTemperature, int daysStoredInFridge, int daysStoredInFreezer) {
        this.foodId = foodId;
        this.name = name;
        this.category = category;
        this.daysStoredRoomTemperature = daysStoredRoomTemperature;
        this.daysStoredInFridge = daysStoredInFridge;
        this.daysStoredInFreezer = daysStoredInFreezer;
    }

    int getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public FoodCategory getCategory() {
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
