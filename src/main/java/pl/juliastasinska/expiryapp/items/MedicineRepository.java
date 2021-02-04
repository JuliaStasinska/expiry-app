package pl.juliastasinska.expiryapp.items;

import org.springframework.data.jpa.repository.JpaRepository;

interface MedicineRepository extends JpaRepository<Medicine, Integer> {

}
