package pl.juliastasinska.expiryapp.templates;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface FoodTemplateRepository extends JpaRepository<FoodTemplate, Integer> {

    List<FoodTemplate> findAll();

    Optional<FoodTemplate> findById(int id);

    <S extends FoodTemplate> S save(S source);

}
