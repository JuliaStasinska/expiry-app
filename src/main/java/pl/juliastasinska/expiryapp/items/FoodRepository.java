package pl.juliastasinska.expiryapp.items;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.juliastasinska.expiryapp.templates.FoodCategory;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findAll();

    //List<Food> findByStorageStatusIsOpenAndFoodCategory(FoodCategory category);

    //List<Food> findByFoodCategory(FoodCategory category);

    List<Food> findByDescriptionContaining(String nameFragment);

    List<Food> findByUseBy(LocalDate date);

    Optional<Food> findById(int id);

    <S extends Food> S save(S source);

    void deleteById(int id);
}
