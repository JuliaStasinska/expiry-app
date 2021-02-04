package pl.juliastasinska.expiryapp.templates.query;

import javax.persistence.*;

@Entity
@Table(name = "medicine_templates")
public class MedicineTemplateQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicineId;
    private String name;
    private int daysStored;

    MedicineTemplateQuery() {
    }

    public MedicineTemplateQuery(int medicineId, String name, int daysStored) {
        this.medicineId = medicineId;
        this.name = name;
        this.daysStored = daysStored;
    }

    int getMedicineId() {
        return medicineId;
    }

    public String getName() {
        return name;
    }

    public int getDaysStored() {
        return daysStored;
    }
}
