package pl.juliastasinska.expiryapp.templates.dto;

public class MedicineTemplateDto {

    private int medicineId;
    private String name;
    private int daysStored;

    public MedicineTemplateDto(int medicineId, String name, int daysStored) {
        this.medicineId = medicineId;
        this.name = name;
        this.daysStored = daysStored;
    }

    public int getId() {
        return medicineId;
    }

    public String getName() {
        return name;
    }

    public int getDaysStored() {
        return daysStored;
    }
}
