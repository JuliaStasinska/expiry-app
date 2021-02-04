package pl.juliastasinska.expiryapp.templates;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.dto.FoodTemplateDto;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "food_templates")
class FoodTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int foodId;
    @NotNull
    private String name;
    @Transient
    private FoodCategory category;
    private int daysStoredRoomTemperature;
    private int daysStoredInFridge;
    private int daysStoredInFreezer;

    FoodTemplate() {
    }

    FoodTemplate(String name, String foodCategory, int daysStoredRoomTemperature, int daysStoredInFridge, int daysStoredInFreezer) {
        this.name = name;
        this.category = FoodCategory.valueOf(foodCategory);
        this.daysStoredRoomTemperature = daysStoredRoomTemperature;
        this.daysStoredInFridge = daysStoredInFridge;
        this.daysStoredInFreezer = daysStoredInFreezer;
    }

    void setName(String name) {
        this.name = name;
    }

    void setCategory(FoodCategory category) {
        this.category = category;
    }

    void setDaysStoredRoomTemperature(int daysStoredRoomTemperature) {
        this.daysStoredRoomTemperature = daysStoredRoomTemperature;
    }

    void setDaysStoredInFridge(int daysStoredInFridge) {
        this.daysStoredInFridge = daysStoredInFridge;
    }

    void setDaysStoredInFreezer(int daysStoredInFreezer) {
        this.daysStoredInFreezer = daysStoredInFreezer;
    }

    FoodTemplateDto toDto(){
        return new FoodTemplateDto(foodId, name, null, daysStoredRoomTemperature, daysStoredInFridge, daysStoredInFreezer);
    }
}
