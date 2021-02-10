package pl.juliastasinska.expiryapp.items;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.juliastasinska.expiryapp.templates.FoodCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface MedicineRepository extends JpaRepository<Medicine, Integer> {

    List<Medicine> findAll();

    //List<Medicine> findByIsOpened();

    //List<Medicine> findByUseBefore(LocalDate date);

    Optional<Medicine> findById(int id);

    <S extends Medicine> S save(S source);

}
