package pl.juliastasinska.expiryapp.templates;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface MedicineTemplateRepository extends JpaRepository<MedicineTemplate, Integer> {

    List<MedicineTemplate> findAll();

    Optional<MedicineTemplate> findById(int id);

    <S extends MedicineTemplate> S save(S source);

}
