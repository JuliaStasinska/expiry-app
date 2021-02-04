package pl.juliastasinska.expiryapp.items;

import com.sun.istack.NotNull;
import pl.juliastasinska.expiryapp.templates.query.MedicineTemplateQuery;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicines")
class Medicine {

    enum UsageStatus{
        WAITING,FINISHED,BINNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int myMedicineId;
    @NotNull
    private String description;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private MedicineTemplateQuery medicine;
    private LocalDate expiryDate;
    private LocalDate useBefore;
    private Boolean opened;
    @Enumerated(EnumType.STRING)
    private UsageStatus status;

    Medicine() {
    }

    Medicine(String description, MedicineTemplateQuery medicine, LocalDate expiryDate) {
        this.description = description;
        this.medicine = medicine;
        this.expiryDate = expiryDate;
        this.useBefore = expiryDate;
        this.opened = false;
        this.status = UsageStatus.WAITING;
    }

    void open(LocalDate openDate){
        this.opened = true;
        useBefore = openDate.plusDays(medicine.getDaysStored());
    }

    void finish(){
        this.status = UsageStatus.FINISHED;
        this.useBefore = null;
    }

    void bin(){
        this.status = UsageStatus.BINNED;
        this.useBefore = null;
    }
}
