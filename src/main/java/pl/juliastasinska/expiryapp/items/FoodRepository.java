package pl.juliastasinska.expiryapp.items;

import org.springframework.data.jpa.repository.JpaRepository;

interface FoodRepository extends JpaRepository<Food, Integer> {

}
