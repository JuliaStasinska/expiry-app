package pl.juliastasinska.expiryapp.templates;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.juliastasinska.expiryapp.templates.dto.DeviceTemplateDto;

import java.util.List;
import java.util.Optional;

interface DeviceTemplateRepository extends JpaRepository<DeviceTemplate, Integer> {

    List<DeviceTemplate> findAll();

    Optional<DeviceTemplate> findById(int id);

    <S extends DeviceTemplate> S save(S source);
}
