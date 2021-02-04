package pl.juliastasinska.expiryapp.templates;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.dto.MedicineTemplateDto;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "medicine_templates")
class MedicineTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicineId;
    @NotNull
    private String name;
    private int daysStored;

    MedicineTemplate() {
    }

    MedicineTemplate(String name, int daysStored) {
        this.name = name;
        this.daysStored = daysStored;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDaysStored(int daysStored) {
        this.daysStored = daysStored;
    }

    MedicineTemplateDto toDto(){
        return new MedicineTemplateDto(medicineId, name, daysStored);
    }
}
