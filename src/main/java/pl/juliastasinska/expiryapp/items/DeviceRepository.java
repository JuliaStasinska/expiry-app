package pl.juliastasinska.expiryapp.items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface DeviceRepository extends JpaRepository<Device, Integer> {

    List<Device> findAll();

    List<Device> findByTimeToClean(LocalDate date);

    List<Device> findByTimeToChangePart(LocalDate date);

    List<Device> findByDescriptionContaining(String nameFragment);

    Optional<Device> findById(int id);

    <S extends Device> S save(S source);

    void deleteById(int id);
}
