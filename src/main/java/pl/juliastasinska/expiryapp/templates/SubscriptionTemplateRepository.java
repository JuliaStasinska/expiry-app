package pl.juliastasinska.expiryapp.templates;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface SubscriptionTemplateRepository extends JpaRepository<SubscriptionTemplate, Integer> {

    List<SubscriptionTemplate> findAll();

    Optional<SubscriptionTemplate> findById(int id);

    <S extends SubscriptionTemplate> S save(S source);


}
